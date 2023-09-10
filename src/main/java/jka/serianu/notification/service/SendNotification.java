package jka.serianu.notification.service;

import jka.serianu.exceptions.ChannelNotFoundException;
import jka.serianu.slackconfiguration.entity.SlackConfigurationEntity;
import jka.serianu.slackconfiguration.repository.SlackConfigurationRepository;
import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class SendNotification {

    private final SlackConfigurationRepository repository;

    public Map<String, String> sendMessage(String channel, String message) throws ChannelNotFoundException, IOException {
        Map<String, String> res = new HashMap<>();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "{\"text\":\"" + message + "\"}");

        Optional<SlackConfigurationEntity> optionalSlackConfiguration = repository.findByChannelId(channel);
        SlackConfigurationEntity slackConfiguration = optionalSlackConfiguration.orElseThrow(() ->
                new ChannelNotFoundException("Missing channel configuration for channel " + channel)
        );

        Request request = new Request.Builder()
                .url(slackConfiguration.getSlackURL())
                .method("POST", body)
                .addHeader("Content-Type", "text/plain")
                .build();
        Response response = client.newCall(request).execute();
        // Get a response as a Java object
        if (response == null) {
            res.put("status", "01");
            res.put("message", "Failed to connect to Slack");
        } else {
            String result = response.toString();
            if (result.contains("ok")) {
                res.put("status", "00");
                res.put("message", "Message sent to Slack successfully");
            } else {
                res.put("status", "01");
                res.put("message", "Could not send message to Slack successfully");
            }
        }
        return res;
    }

    public List<SlackConfigurationEntity> fetchAllConfiguration() {
        return repository.findAll();
    }
}
