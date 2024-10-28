package com.saga.bitbucket.persistence.repository.document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Setter
@Getter
@Document("PULL_REQUEST")
public class PullRequestDocument {
  @Id
  private String id;

  private String prRequestId;
  private String branchId;
  private Double amount;
  private String externalId;
  private String pullRequestStatus;
}
 