package com.saga.orchestrator.persistence.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.saga.orchestrator.persistence.repository.model.PrRequestDocument;

public interface PrRequestDocumentRepository extends MongoRepository<PrRequestDocument, String> {}
