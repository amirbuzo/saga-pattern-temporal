package com.saga.bitbucket.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saga.bitbucket.application.domain.model.PullRequestDto;
import com.saga.bitbucket.application.domain.repository.PullRequestRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@AllArgsConstructor
public class BitBucketController {
    
    private final PullRequestRepository prRepository;
    
    @GetMapping("/bitbucketpr")
    public ResponseEntity<List<PullRequestDto>> listPr() {
        log.info("Getting all bitbucket pr..");
        // For simplicity, keep API model same as domain model
        List<PullRequestDto> pullRequests = prRepository.getAll();
        return new ResponseEntity<>(pullRequests, HttpStatus.OK);
    }
}
