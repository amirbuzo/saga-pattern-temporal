package com.ikubinfo.common.activities;

import com.ikubinfo.common.model.JiraTaskDTO;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface BuildActivities {
  void buildProject(JiraTaskDTO prRequestDTO);
  void cancelCleanProject(JiraTaskDTO prRequestDTO);
}
