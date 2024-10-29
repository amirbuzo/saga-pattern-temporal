package com.saga.sonar.infrastructure.temporal.workflow.activity.impl;

import java.util.Random;

import com.saga.common.activities.SonarActivities;
import com.saga.common.error.ResourceNotFoundException;
import com.saga.common.model.JiraTaskDTO;
import com.saga.sonar.application.domain.model.SonarResultDto;
import com.saga.sonar.application.domain.model.SonarStatus;
import com.saga.sonar.application.domain.repository.SonarRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SonarActivitiesImpl implements SonarActivities {
    private static final Random RANDOM_BOOLEAN = new Random();
    private final SonarRepository sonarRepository;
    
    @Override
    public boolean checkSonar(JiraTaskDTO taskDTO) {
        
        log.info("Processing sonar for task {}", taskDTO.getJiraTaskId());
        
        var sonar = SonarResultDto.builder().taskId(taskDTO.getJiraTaskId()).branchId(taskDTO.getBranchId()).path(taskDTO.getPath()).sonarStatus(SonarStatus.KO).build();
        boolean result = RANDOM_BOOLEAN.nextBoolean();
        if (result) {
            sonar.setSonarStatus(SonarStatus.OK);
        }
        sonarRepository.save(sonar);
        
        log.info("Finished processing sonar for task {}", taskDTO.getJiraTaskId());
        
        return result;
    }
    
    @Override
    public void cleanSonar(JiraTaskDTO taskDTO) {
        log.info("Cleaning sonar for branch {}", taskDTO.getBranchId());
        var sonar = sonarRepository.getByBranchId(taskDTO.getBranchId()).orElseThrow(() -> new ResourceNotFoundException("branch id not found"));
        sonar.setSonarStatus(SonarStatus.KO);
        sonarRepository.save(sonar);
    }
}
