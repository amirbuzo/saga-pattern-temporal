package com.ikubinfo.sonar.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ikubinfo.sonar.application.domain.model.SonarResultDto;
import com.ikubinfo.sonar.application.domain.repository.SonarRepository;

import java.util.List;

@RestController
@Slf4j
public class SonarController {
  @Autowired private SonarRepository sonarRepository;

  @GetMapping("/sonar")
  public ResponseEntity<List<SonarResultDto>> listSonar() {
    log.info("Getting all sonar checks..");
    List<SonarResultDto> sonar = sonarRepository.getAll();
    return new ResponseEntity<>(sonar, HttpStatus.OK);
  }
}
