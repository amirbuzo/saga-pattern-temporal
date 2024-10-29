package com.saga.sonar.resource;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saga.sonar.application.domain.model.SonarResultDto;
import com.saga.sonar.application.domain.repository.SonarRepository;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class SonarController {
    
    private final SonarRepository sonarRepository;
    
    @GetMapping("/sonar")
    public ResponseEntity<List<SonarResultDto>> listSonar() {
        log.info("Getting all sonar checks..");
        List<SonarResultDto> sonar = sonarRepository.getAll();
        return new ResponseEntity<>(sonar, HttpStatus.OK);
    }
}
