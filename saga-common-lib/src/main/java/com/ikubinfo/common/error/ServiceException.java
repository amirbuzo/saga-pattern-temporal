package com.ikubinfo.common.error;

public class ServiceException extends RuntimeException {
  public ServiceException(String message) {
    super(message);
  }
}
