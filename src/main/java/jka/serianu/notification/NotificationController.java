package jka.serianu.notification;

import jka.serianu.exceptions.ChannelNotFoundException;
import jka.serianu.notification.dto.SlackMessage;
import jka.serianu.notification.service.SendNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Controller
public class NotificationController {
    private final SendNotification sendNotification;

    @GetMapping(value = "/notifications")
    public String slackConfigurationForm( Model model) {
        model.addAttribute("slackConfigs", sendNotification.fetchAllConfiguration());
        return "index";
    }

    @PostMapping(value = "/notifications")
    @ResponseBody
    public Map<String, String> slackConfigurationPosting(@RequestBody SlackMessage slackMessage)
            throws IOException, ChannelNotFoundException {
        log.info("Message to be posted {}", slackMessage);
        return sendNotification.sendMessage(slackMessage.getChannelId(), slackMessage.getMessage());
    }
}
