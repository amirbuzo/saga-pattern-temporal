package com.ikubinfo.sonar.persistence.repository.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ikubinfo.sonar.persistence.repository.model.SonarResultDocument;

@Repository
public interface SonarMongoRepository extends MongoRepository<SonarResultDocument, String> {
    List<SonarResultDocument> findByprRequestId(String prRequestId);
    List<SonarResultDocument> findByBranchId(String prRequestId);
}
