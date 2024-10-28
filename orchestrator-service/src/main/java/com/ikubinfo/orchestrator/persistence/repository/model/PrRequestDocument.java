package com.ikubinfo.orchestrator.persistence.repository.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Setter
@Getter
@Document("PULL_REQUEST_DOCUMENT")
public class PrRequestDocument {
  
	@Id
  private String id;

  private String branchId;
  private String prRequestStatus;
  private String user;
  private String path;
}
