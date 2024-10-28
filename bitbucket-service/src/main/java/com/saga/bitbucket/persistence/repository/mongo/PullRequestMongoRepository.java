package com.saga.bitbucket.persistence.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.saga.bitbucket.persistence.repository.document.PullRequestDocument;

import java.util.List;

@Repository
public interface PullRequestMongoRepository extends MongoRepository<PullRequestDocument, String> {
  List<PullRequestDocument> findByprRequestId(String prRequestId);
}
