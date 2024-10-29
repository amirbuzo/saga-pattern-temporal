package com.saga.sonar.persistence.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.saga.sonar.application.domain.model.SonarStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Document("SONAR_RESULT")
public class SonarResultDocument {
    
    @Id
    private String id;
    
    private String prRequestId;
    private String branchId;
    private String path;
    private String taskId;
    private SonarStatus sonarStatus;
}
