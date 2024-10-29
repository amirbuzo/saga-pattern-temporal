package com.saga.common.activities;

import com.saga.common.model.JiraTaskDTO;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface SonarActivities {
    boolean checkSonar(JiraTaskDTO prRequestDTO);
    
    void cleanSonar(JiraTaskDTO prRequestDTO);
}
