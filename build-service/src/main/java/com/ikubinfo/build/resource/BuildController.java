package com.ikubinfo.build.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ikubinfo.build.application.domain.model.BuildArtifactDto;
import com.ikubinfo.build.application.domain.repository.BuildArtifactRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BuildController {
  private final BuildArtifactRepository buildRepository;

  @GetMapping("/build")
  public ResponseEntity<List<BuildArtifactDto>> listBuildment() {
    log.info("Getting all builds..");
    List<BuildArtifactDto> builds = buildRepository.getAll();
    return new ResponseEntity<>(builds, HttpStatus.OK);
  }
}
