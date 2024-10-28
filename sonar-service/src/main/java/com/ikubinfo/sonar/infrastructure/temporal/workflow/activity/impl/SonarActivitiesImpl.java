package com.ikubinfo.sonar.infrastructure.temporal.workflow.activity.impl;

import java.util.Random;

import com.ikubinfo.common.activities.SonarActivities;
import com.ikubinfo.common.error.ResourceNotFoundException;
import com.ikubinfo.common.model.JiraTaskDTO;
import com.ikubinfo.sonar.application.domain.model.SonarResultDto;
import com.ikubinfo.sonar.application.domain.model.SonarStatus;
import com.ikubinfo.sonar.application.domain.repository.SonarRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SonarActivitiesImpl implements SonarActivities {

  private final SonarRepository sonarRepository;

  @Override
  public boolean checkSonar(JiraTaskDTO taskDTO) {

    log.info("Processing sonar for task {}", taskDTO.getJiraTaskId());

    var sonar =
        SonarResultDto.builder()
            .taskId(taskDTO.getJiraTaskId())
            .branchId(taskDTO.getBranchId())
            .path(taskDTO.getPath())
            .sonarStatus(SonarStatus.KO)
            .build();
     boolean result= new Random().nextBoolean();
     if(result)
     {
    	 sonar.setSonarStatus(SonarStatus.OK);
     }
     sonarRepository.save(sonar);
    
     log.info("Finished processing sonar for task {}", taskDTO.getJiraTaskId());

     return result;
  }

  @Override
  public void cleanSonar(JiraTaskDTO taskDTO) {
    log.info("Cleaning sonar for branch {}", taskDTO.getBranchId());
    var sonar =
            sonarRepository
                    .getByBranchId(taskDTO.getBranchId())
                    .orElseThrow(() -> new ResourceNotFoundException("branch id not found"));
    sonar.setSonarStatus(SonarStatus.KO);
    sonarRepository.save(sonar);
  }
}
