package jka.serianu.slackconfiguration;

import jka.serianu.slackconfiguration.entity.SlackConfigurationEntity;
import jka.serianu.slackconfiguration.service.SlackConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
public class SlackConfigurationController {
    private final SlackConfigService service;

    @GetMapping(value = "/slack-configurations")
    public String slackConfigurationForm() {
        System.err.println("=============================");
        return "slack-configurations";
    }

    @PostMapping("/slack-configurations")
    @ResponseBody
    public Map<String, String> addSlackConfigurations(@RequestBody SlackConfigurationEntity request) {
        log.info("Slack configuration - {}", request);
        return service.save(request);
    }
}
