package jka.serianu.slackconfiguration.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "SLACK_CONFIG", uniqueConstraints = {@UniqueConstraint(columnNames = {"CHANNEL_ID"})})
public class SlackConfigurationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private long id;

    @Column(name = "CHANNEL_ID")
    private String channelId;

    @Column(name = "SLACK_URL")
    private String slackURL;
}
