package com.ikubinfo.orchestrator.infrastructure.temporal.workflow.activity.impl;

import com.ikubinfo.common.activities.JiraTaskActivities;
import com.ikubinfo.common.model.JiraTaskDTO;
import com.ikubinfo.orchestrator.application.domain.model.PrRequest;
import com.ikubinfo.orchestrator.application.domain.model.PrStatus;
import com.ikubinfo.orchestrator.application.domain.repository.PrRequestRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JiraTaskActivitiesImpl implements JiraTaskActivities {

  private final PrRequestRepository taskRepository;

  @Override
  public void completeTask(JiraTaskDTO taskDTO) {
    log.info("Marking task as completed, task id {}", taskDTO.getJiraTaskId());
    var task = map(taskDTO);
    task.setPrRequestStatus(PrStatus.COMPLETED);
    var completedprRequest = taskRepository.save(task);
    log.info("PrRequestDto completed, {}", completedprRequest);
  }

  @Override
  public void failTask(JiraTaskDTO taskDTO) {
    var task = taskRepository.get(taskDTO.getJiraTaskId());
    task.setPrRequestStatus(PrStatus.FAILED);
    taskRepository.save(task);
  }

  private PrRequest map(JiraTaskDTO taskDTO) {
    var task = new PrRequest();
    task.setPrRequestId(taskDTO.getJiraTaskId());
    task.setBranchId(taskDTO.getBranchId());
    task.setUser(taskDTO.getUser());
    task.setPath(taskDTO.getPath());
    return task;
  }
}
