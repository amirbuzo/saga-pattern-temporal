package com.saga.common.activities;

import com.saga.common.model.JiraTaskDTO;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface BitbucketActivities {
    void requestPr(JiraTaskDTO prRequestDTO);
    
    void cancelCleanPr(JiraTaskDTO prRequestDTO);
}
