package com.saga.orchestrator.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.saga.orchestrator.application.command.PullRequestCommand;
import com.saga.orchestrator.application.domain.model.PrRequest;
import com.saga.orchestrator.application.domain.repository.PrRequestRepository;
import com.saga.orchestrator.application.factory.PrRequestFactory;
import com.saga.orchestrator.controller.mapper.PrRequestMapper;
import com.saga.orchestrator.controller.model.PrRequestDto;
import com.saga.orchestrator.controller.model.PrResponse;
import com.saga.orchestrator.persistence.repository.model.PrRequestDocument;
import com.saga.orchestrator.persistence.repository.mongo.PrRequestDocumentRepository;

import jakarta.validation.Valid;

@RestController
@Slf4j
public class PrRequestController {
  @Autowired private PrRequestFactory prRequestFactory;
  @Autowired
  private   PrRequestDocumentRepository prRequestRepository;

  @PostMapping("/pr")
  public ResponseEntity<PrResponse> createPr(@Valid @RequestBody PrRequestDto prRequestRequest) {

    log.info("Creating pr, pr id {}", prRequestRequest.getBranchId());

    var prRequest = PrRequestMapper.MAPPER.map(prRequestRequest);
    PullRequestCommand prRequestCommand = prRequestFactory.getOrderCommand();

    PrRequest pendingPr = prRequestCommand.createPrRequest(prRequest);

    var prRequestResponse = new PrResponse(pendingPr.getPrRequestId(), pendingPr.getPrRequestStatus());
    return new ResponseEntity<>(prRequestResponse, HttpStatus.ACCEPTED);
  }

  @GetMapping("/pr/{id}")
  public ResponseEntity<PrResponse> getPr(@PathVariable String id) {

    log.info("Getting prRequest, id {}", id);

    var prRequestQuery = prRequestFactory.getOrderQuery();

    var prRequest = prRequestQuery.getprRequest(id);

    var prRequestResponse = new PrResponse(prRequest.getPrRequestId(), prRequest.getPrRequestStatus());
    return new ResponseEntity<>(prRequestResponse, HttpStatus.OK);
  }
  
  @GetMapping("/pr")
  public ResponseEntity<List<PrRequestDocument>> getPr() {

    log.info("Getting prRequest,  " );
  
     return new ResponseEntity<>(prRequestRepository.findAll(), HttpStatus.OK);
  }
}
