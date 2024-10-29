package com.saga.orchestrator.common.config;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.saga.common.activities.JiraTaskActivities;
import com.saga.orchestrator.application.command.PullRequestCommand;
import com.saga.orchestrator.application.command.impl.PrRequestCommandImpl;
import com.saga.orchestrator.application.domain.repository.PrRequestRepository;
import com.saga.orchestrator.application.factory.PrRequestFactory;
import com.saga.orchestrator.application.factory.impl.PrRequestFactoryImpl;
import com.saga.orchestrator.application.orchestration.WorkflowOrchestrator;
import com.saga.orchestrator.application.query.PrRequestQuery;
import com.saga.orchestrator.application.query.impl.PrRequestQueryImpl;
import com.saga.orchestrator.infrastructure.temporal.orchestrator.WorkflowOrchestratorClient;
import com.saga.orchestrator.infrastructure.temporal.orchestrator.WorkflowOrchestratorImpl;
import com.saga.orchestrator.infrastructure.temporal.worker.PrWorker;
import com.saga.orchestrator.infrastructure.temporal.workflow.activity.impl.JiraTaskActivitiesImpl;
import com.saga.orchestrator.persistence.repository.PrRequestRepositoryImpl;
import com.saga.orchestrator.persistence.repository.mongo.PrRequestDocumentRepository;

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
        return new PrWorker(createPendingprRequestActivity(prRequestJpaRepository), workflowOrchestratorClient());
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
