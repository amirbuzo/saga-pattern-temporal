package com.ikubinfo.bitbucket.config;

import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ikubinfo.bitbucket.application.domain.repository.PullRequestRepository;
import com.ikubinfo.bitbucket.application.service.PullRequestService;
import com.ikubinfo.bitbucket.application.service.impl.PullRequestServiceImpl;
import com.ikubinfo.bitbucket.infrastructure.temporal.orchestrator.WorkflowOrchestratorClient;
import com.ikubinfo.bitbucket.infrastructure.temporal.orchestrator.worker.BitBucketWorker;
import com.ikubinfo.bitbucket.infrastructure.temporal.workflow.activity.impl.BitBucketActivitiesImpl;
import com.ikubinfo.bitbucket.persistence.repository.PullRequestRepositoryImpl;
import com.ikubinfo.bitbucket.persistence.repository.mongo.PullRequestMongoRepository;
import com.ikubinfo.common.activities.BitbucketActivities;

@Configuration
@Setter
public class BitBucketServiceAppConfig {

  @Bean
  public BitBucketWorker pullRequestWorker(PullRequestMongoRepository pullRequestMongoRepository) {
    return new BitBucketWorker(
        jiraActivity(pullRequestMongoRepository), workflowOrchestratorClient());
  }

  @Bean
  public BitbucketActivities jiraActivity(PullRequestMongoRepository pullRequestMongoRepository) {
    return new BitBucketActivitiesImpl(pullRequestService(), new PullRequestRepositoryImpl(pullRequestMongoRepository));
  }

  @Bean
  public PullRequestService pullRequestService() {
    return new PullRequestServiceImpl();
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

  
}
