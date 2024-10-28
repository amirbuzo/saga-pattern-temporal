package com.ikubinfo.common.activities;

import com.ikubinfo.common.model.JiraTaskDTO;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface SonarActivities {
  boolean checkSonar(JiraTaskDTO prRequestDTO);
  void cleanSonar(JiraTaskDTO prRequestDTO);
}
