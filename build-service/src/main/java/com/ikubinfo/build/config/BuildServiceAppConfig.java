package com.ikubinfo.build.config;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ikubinfo.build.application.domain.repository.BuildArtifactRepository;
import com.ikubinfo.build.application.domain.service.BuildService;
import com.ikubinfo.build.application.domain.service.impl.BuildArtifactServiceImpl;
import com.ikubinfo.build.infrastructure.temporal.orchestrator.WorkflowOrchestratorClient;
import com.ikubinfo.build.infrastructure.temporal.worker.BuildWorker;
import com.ikubinfo.build.infrastructure.temporal.workflow.activity.impl.BuildActivitiesImpl;
import com.ikubinfo.build.persistence.repository.BuildRepositoryImpl;
import com.ikubinfo.build.persistence.repository.mongo.BuildMongoRepository;
import com.ikubinfo.common.activities.BuildActivities;
 

@Configuration
@Setter
public class BuildServiceAppConfig {

  @Bean
  public BuildWorker buildWorker(BuildMongoRepository buildMongoRepository) {
    return new BuildWorker(
        buildProjectActivity(buildMongoRepository), workflowOrchestratorClient());
  }

  @Bean
  public BuildActivities buildProjectActivity(BuildMongoRepository buildMongoRepository) {
    return new BuildActivitiesImpl(buildService(), buildRepository(buildMongoRepository));
  }

  @Bean
  public BuildService buildService() {
    return new BuildArtifactServiceImpl(applicationProperties());
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
  public BuildArtifactRepository buildRepository(BuildMongoRepository buildMongoRepository) {
    return new BuildRepositoryImpl(buildMongoRepository);
  }
}
