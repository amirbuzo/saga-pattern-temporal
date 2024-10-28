package com.saga.bitbucket.config;

import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.saga.bitbucket.application.domain.repository.PullRequestRepository;
import com.saga.bitbucket.application.service.PullRequestService;
import com.saga.bitbucket.application.service.impl.PullRequestServiceImpl;
import com.saga.bitbucket.infrastructure.temporal.orchestrator.WorkflowOrchestratorClient;
import com.saga.bitbucket.infrastructure.temporal.orchestrator.worker.BitBucketWorker;
import com.saga.bitbucket.infrastructure.temporal.workflow.activity.impl.BitBucketActivitiesImpl;
import com.saga.bitbucket.persistence.repository.PullRequestRepositoryImpl;
import com.saga.bitbucket.persistence.repository.mongo.PullRequestMongoRepository;
import com.saga.common.activities.BitbucketActivities;

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
