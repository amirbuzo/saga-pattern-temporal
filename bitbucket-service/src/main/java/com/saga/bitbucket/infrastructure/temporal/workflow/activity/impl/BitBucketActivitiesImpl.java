package com.saga.bitbucket.infrastructure.temporal.workflow.activity.impl;

import com.saga.bitbucket.application.domain.model.PullRequestDto;
import com.saga.bitbucket.application.domain.model.PullRequestStatus;
import com.saga.bitbucket.application.domain.repository.PullRequestRepository;
import com.saga.bitbucket.application.service.PullRequestService;
import com.saga.common.activities.BitbucketActivities;
import com.saga.common.error.ResourceNotFoundException;
import com.saga.common.model.JiraTaskDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class BitBucketActivitiesImpl implements BitbucketActivities {

  private final PullRequestService pullRequestService;
  private final PullRequestRepository pullRequestRepository;

  @Override
  public void requestPr(JiraTaskDTO prRequestDTO) {
    log.info("Processing pullRequest for prRequest {}", prRequestDTO.getJiraTaskId());
    String path = prRequestDTO.getPath() ;
    // Call external PullRequestDto service such as Stripe
    var externalJiraId = pullRequestService.checkPath(path);
    // Create domain object
    var pullRequest =
        PullRequestDto.builder()
            .externalId(externalJiraId)
            .prRequestId(prRequestDTO.getJiraTaskId())
            .branchId(prRequestDTO.getBranchId())
            .path(path)
            .pullRequestStatus(PullRequestStatus.ACTIVE)
            .build();
    pullRequestRepository.save(pullRequest);
  }

  @Override
  public void cancelCleanPr(JiraTaskDTO prRequestDTO) {
    log.info("Reversing pullRequest for prRequest {}", prRequestDTO.getJiraTaskId());
    PullRequestDto pullRequest =
        pullRequestRepository
            .getByprRequestId(prRequestDTO.getJiraTaskId())
            .orElseThrow(() -> new ResourceNotFoundException("prRequest id not found"));
    pullRequest.setPullRequestStatus(PullRequestStatus.REVERSED);
    pullRequestRepository.save(pullRequest);
  }
}
