package com.ikubinfo.build.persistence.repository.mongo;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.ikubinfo.build.persistence.repository.document.BuildArtifactDocument;

public interface BuildMongoRepository extends MongoRepository<BuildArtifactDocument, String> {}
