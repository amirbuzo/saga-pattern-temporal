package com.ikubinfo.orchestrator.common.config;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ikubinfo.common.activities.JiraTaskActivities;
import com.ikubinfo.orchestrator.application.command.PullRequestCommand;
import com.ikubinfo.orchestrator.application.command.impl.PrRequestCommandImpl;
import com.ikubinfo.orchestrator.application.domain.repository.PrRequestRepository;
import com.ikubinfo.orchestrator.application.factory.PrRequestFactory;
import com.ikubinfo.orchestrator.application.factory.impl.PrRequestFactoryImpl;
import com.ikubinfo.orchestrator.application.orchestration.WorkflowOrchestrator;
import com.ikubinfo.orchestrator.application.query.PrRequestQuery;
import com.ikubinfo.orchestrator.application.query.impl.PrRequestQueryImpl;
import com.ikubinfo.orchestrator.infrastructure.temporal.orchestrator.WorkflowOrchestratorClient;
import com.ikubinfo.orchestrator.infrastructure.temporal.orchestrator.WorkflowOrchestratorImpl;
import com.ikubinfo.orchestrator.infrastructure.temporal.worker.PrWorker;
import com.ikubinfo.orchestrator.infrastructure.temporal.workflow.activity.impl.JiraTaskActivitiesImpl;
import com.ikubinfo.orchestrator.persistence.repository.PrRequestRepositoryImpl;
import com.ikubinfo.orchestrator.persistence.repository.mongo.PrRequestDocumentRepository;

@Configuration
@Setter
public class OrchestratorServiceAppConfig {

  @Bean
  public PrRequestFactory prRequestFactory(PrRequestDocumentRepository prRequestJpaRepository) {
    return new PrRequestFactoryImpl(prRequestCommand(prRequestJpaRepository), prRequestQuery(prRequestJpaRepository));
  }

  private PrRequestQuery prRequestQuery(PrRequestDocumentRepository prRequestJpaRepository) {
    return new PrRequestQueryImpl(prRequestRepository(prRequestJpaRepository));
  }

  @Bean
  public PullRequestCommand prRequestCommand(PrRequestDocumentRepository prRequestJpaRepository) {
    return new PrRequestCommandImpl(prRequestRepository(prRequestJpaRepository), workflowOrchestrator());
  }

  @Bean
  public PrWorker prRequestWorker(PrRequestDocumentRepository prRequestJpaRepository) {
    return new PrWorker(
        createPendingprRequestActivity(prRequestJpaRepository), workflowOrchestratorClient());
  }

  @Bean
  public JiraTaskActivities createPendingprRequestActivity(PrRequestDocumentRepository prRequestJpaRepository) {
    return new JiraTaskActivitiesImpl(prRequestRepository(prRequestJpaRepository));
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
  public PrRequestRepository prRequestRepository(PrRequestDocumentRepository prRequestJpaRepository) {
    return new PrRequestRepositoryImpl(prRequestJpaRepository);
  }

  @Bean
  public WorkflowOrchestrator workflowOrchestrator() {
    return new WorkflowOrchestratorImpl(workflowOrchestratorClient(), applicationProperties());
  }
}
