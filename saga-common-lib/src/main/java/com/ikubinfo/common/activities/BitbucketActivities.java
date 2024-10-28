package com.ikubinfo.common.activities;

import com.ikubinfo.common.model.JiraTaskDTO;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface BitbucketActivities {
  void requestPr(JiraTaskDTO prRequestDTO);
  void cancelCleanPr(JiraTaskDTO prRequestDTO);
}
