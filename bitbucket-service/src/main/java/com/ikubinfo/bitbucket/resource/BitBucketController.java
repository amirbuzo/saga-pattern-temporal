package com.ikubinfo.bitbucket.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ikubinfo.bitbucket.application.domain.model.PullRequestDto;
import com.ikubinfo.bitbucket.application.domain.repository.PullRequestRepository;

import java.util.List;

@RestController
@Slf4j
public class BitBucketController {
  @Autowired private PullRequestRepository prRepository;

  @GetMapping("/bitbucketpr")
  public ResponseEntity<List<PullRequestDto>> listPr() {
    log.info("Getting all bitbucket pr..");
    // For simplicity, keep API model same as domain model
    List<PullRequestDto> pullRequests = prRepository.getAll();
    return new ResponseEntity<>(pullRequests, HttpStatus.OK);
  }
}
