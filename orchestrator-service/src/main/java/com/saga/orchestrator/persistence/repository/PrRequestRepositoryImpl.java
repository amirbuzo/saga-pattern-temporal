package com.saga.orchestrator.persistence.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import com.saga.common.error.ResourceNotFoundException;
import com.saga.orchestrator.application.domain.model.PrRequest;
import com.saga.orchestrator.application.domain.repository.PrRequestRepository;
import com.saga.orchestrator.persistence.mapper.PrRequestMapper;
import com.saga.orchestrator.persistence.repository.mongo.PrRequestDocumentRepository;

@Slf4j
public class PrRequestRepositoryImpl implements PrRequestRepository {

  private final PrRequestDocumentRepository prRequestMongoRepository;

  public PrRequestRepositoryImpl(PrRequestDocumentRepository prRequestMongoRepository) {
    this.prRequestMongoRepository = prRequestMongoRepository;
  }

  @Override
  @Transactional
  public PrRequest save(PrRequest prRequest) {
    log.info("Saving PrRequestDto");
    var prRequestPersistable = PrRequestMapper.MAPPER.map(prRequest);
    prRequestPersistable = prRequestMongoRepository.save(prRequestPersistable);
    log.info("PrRequestDto saved, prRequest {}", prRequestPersistable);
    return PrRequestMapper.MAPPER.map(prRequestPersistable);
  }

  @Override
  @Transactional
  public PrRequest get(String prRequestId) {
    log.info("Fetching prRequest for id {}", prRequestId);
    var prRequestPersistable =
        prRequestMongoRepository
            .findById(prRequestId)
            .orElseThrow(() -> new ResourceNotFoundException("PrRequestDto not found"));
    log.info("Fetched prRequest, {}", prRequestPersistable);
    return PrRequestMapper.MAPPER.map(prRequestPersistable);
  }
}
