package jka.serianu.slackconfiguration.repository;

import jka.serianu.slackconfiguration.entity.SlackConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SlackConfigurationRepository extends JpaRepository<SlackConfigurationEntity, Long> {
    Optional<SlackConfigurationEntity> findByChannelId(String channelId);
}
