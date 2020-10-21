#!/bin/bash

sudo chmod 777 -R DOCKER/volumes
mvn clean package
cd DOCKER
docker-compose up --build



