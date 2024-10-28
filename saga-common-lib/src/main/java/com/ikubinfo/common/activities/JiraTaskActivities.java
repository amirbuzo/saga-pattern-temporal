package com.ikubinfo.common.activities;

import com.ikubinfo.common.model.JiraTaskDTO;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface JiraTaskActivities {
  void completeTask(JiraTaskDTO prRequest);
  void failTask(JiraTaskDTO prRequestDTO);
}
