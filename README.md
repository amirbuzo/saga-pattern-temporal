# Introduction

The code for IADL with Saga pattern

.

## Modules

### saga-common-lib

Contains common classes and interfaces but no implemenation


### orchestrator-service

This microservice is an orchestartor and entry point and stores the pr request.

clean/hexagonal architecture

- application: Bussiness logic but there is no direct dependency on Temporal code.
- common: Spring Boot configuration bean
- infrastructure: Temporal Workflow, Activity and Worker.
- persistence: Mongo.
- resource: REST API

### build-service 

### jira-service

### sonar-service

## Tempral
On local Docker

````commandline
git clone https://github.com/temporalio/docker-compose.git
cd  docker-compose
docker-compose up
````

# Build and Test

clean install
docker compose up