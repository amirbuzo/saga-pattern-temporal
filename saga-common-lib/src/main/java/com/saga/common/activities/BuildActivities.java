package com.saga.common.activities;

import com.saga.common.model.JiraTaskDTO;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface BuildActivities {
    void buildProject(JiraTaskDTO prRequestDTO);
    
    void cancelCleanProject(JiraTaskDTO prRequestDTO);
}
