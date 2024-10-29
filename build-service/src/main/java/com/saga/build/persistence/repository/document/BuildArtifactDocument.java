package com.saga.build.persistence.repository.document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Setter
@Getter
@Document("BUILD_DOCUMENT")
public class BuildArtifactDocument {
    @Id
    private String id;
    
    private String prRequestId;
    private String branchId;
    private String path;
    private String trackingId;
}
