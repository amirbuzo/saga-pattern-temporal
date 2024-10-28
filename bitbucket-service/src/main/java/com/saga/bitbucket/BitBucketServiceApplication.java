package com.saga.bitbucket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class BitBucketServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(BitBucketServiceApplication.class, args);
  }
}
