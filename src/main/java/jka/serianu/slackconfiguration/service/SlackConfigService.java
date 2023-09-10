package jka.serianu.slackconfiguration.service;

import jka.serianu.slackconfiguration.entity.SlackConfigurationEntity;
import jka.serianu.slackconfiguration.repository.SlackConfigurationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SlackConfigService {
    private final SlackConfigurationRepository repository;

    public Map<String, String> save(SlackConfigurationEntity request) {
        log.info("Received slack configuration {}", request);
        Map<String, String> res = new HashMap<>();
        Optional<SlackConfigurationEntity> optionalSlackConfiguration = repository.findByChannelId(request.getChannelId());

        if (optionalSlackConfiguration.isEmpty()) {
            SlackConfigurationEntity slackConfiguration = new SlackConfigurationEntity();
            slackConfiguration.setSlackURL(request.getSlackURL());
            slackConfiguration.setChannelId(request.getChannelId());
            repository.save(slackConfiguration);
        } else {
            SlackConfigurationEntity slackConfigurationEntity = optionalSlackConfiguration.get();
            slackConfigurationEntity.setSlackURL(request.getSlackURL());
            slackConfigurationEntity.setChannelId(request.getChannelId());
            repository.save(slackConfigurationEntity);
        }
        res.put("status", "00");
        res.put("message", "Transaction processed successful");
        return res;
    }
}
