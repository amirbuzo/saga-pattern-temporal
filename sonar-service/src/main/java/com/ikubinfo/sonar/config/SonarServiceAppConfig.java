package com.ikubinfo.sonar.config;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ikubinfo.common.activities.SonarActivities;
import com.ikubinfo.sonar.application.domain.repository.SonarRepository;
import com.ikubinfo.sonar.infrastructure.temporal.orchestrator.WorkflowOrchestratorClient;
import com.ikubinfo.sonar.infrastructure.temporal.worker.SonarWorker;
import com.ikubinfo.sonar.infrastructure.temporal.workflow.activity.impl.SonarActivitiesImpl;
import com.ikubinfo.sonar.persistence.repository.SonarRepositoryImpl;
import com.ikubinfo.sonar.persistence.repository.mongo.SonarMongoRepository;

@Configuration
@Setter
public class SonarServiceAppConfig {

  @Bean
  public SonarWorker buildWorker(SonarMongoRepository buildJpaRepository) {
    return new SonarWorker(
        reserveSonarActivity(buildJpaRepository), workflowOrchestratorClient());
  }

  @Bean
  public SonarActivities reserveSonarActivity(
      SonarMongoRepository buildJpaRepository) {
    return new SonarActivitiesImpl(buildRepository(buildJpaRepository));
  }

  @Bean
  @ConfigurationProperties
  public ApplicationProperties applicationProperties() {
    return new ApplicationProperties();
  }

  @Bean
  public WorkflowOrchestratorClient workflowOrchestratorClient() {
    return new WorkflowOrchestratorClient(applicationProperties());
  }

  @Bean
  public SonarRepository buildRepository(SonarMongoRepository buildJpaRepository) {
    return new SonarRepositoryImpl(buildJpaRepository);
  }
}
