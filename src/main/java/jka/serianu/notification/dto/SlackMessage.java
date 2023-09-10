package jka.serianu.notification.dto;

import lombok.Data;

@Data
public class SlackMessage {
    private String channelId;
    private String message;
}
