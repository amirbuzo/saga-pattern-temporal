package com.saga.common.activities;

import com.saga.common.model.JiraTaskDTO;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface JiraTaskActivities {
  void completeTask(JiraTaskDTO prRequest);
  void failTask(JiraTaskDTO prRequestDTO);
}
