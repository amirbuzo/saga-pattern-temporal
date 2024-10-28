package com.ikubinfo.orchestrator.persistence.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ikubinfo.orchestrator.persistence.repository.model.PrRequestDocument;

public interface PrRequestDocumentRepository extends MongoRepository<PrRequestDocument, String> {}
