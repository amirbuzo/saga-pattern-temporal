package com.ikubinfo.build.infrastructure.temporal.workflow.activity.impl;

import com.ikubinfo.build.application.domain.model.BuildArtifactDto;
import com.ikubinfo.build.application.domain.repository.BuildArtifactRepository;
import com.ikubinfo.build.application.domain.service.BuildService;
import com.ikubinfo.common.activities.BuildActivities;
import com.ikubinfo.common.model.JiraTaskDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class BuildActivitiesImpl implements BuildActivities {

  private final BuildService buildService;
  private final BuildArtifactRepository buildRepository;

  @Override
  public void buildProject(JiraTaskDTO prRequestDTO) {

    log.info("Intiated build,  branch id {}", prRequestDTO.getBranchId());
    var trackingId = buildService.buildArtifact(prRequestDTO.getPath());

    var build =
        BuildArtifactDto.builder()
            .prRequestId(prRequestDTO.getJiraTaskId())
            .branchId(prRequestDTO.getBranchId())
            .path(prRequestDTO.getPath())
            .trackingId(trackingId)
            .build();
    buildRepository.save(build);

    log.info("Created build for prRequest id {}", prRequestDTO.getJiraTaskId());
  }

  @Override
  public void cancelCleanProject(JiraTaskDTO prRequestDTO) {
	    log.info("Clean build for prRequest id {}", prRequestDTO.getJiraTaskId());

  }
}
