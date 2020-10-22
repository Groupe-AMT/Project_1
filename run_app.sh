#!/bin/bash

mvn clean package
cd DOCKER
docker-compose up --build



