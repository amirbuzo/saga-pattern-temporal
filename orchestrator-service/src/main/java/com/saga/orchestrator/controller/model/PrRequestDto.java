package com.saga.orchestrator.controller.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Setter
@Getter
@ToString
public class PrRequestDto {

  @NotNull(message = "Branch ID is required.")
  @NotBlank
  private String branchId;

  @NotNull(message = "User is required.")
  @NotBlank
  private String user;

  @NotNull(message = "Path is required.")
  @NotBlank
  private String path;
}
