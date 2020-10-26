#!/bin/bash
mvn liberty:stop -q
# cd DOCKER
# docker-compose up --build mysql phpmyadmin &
# cd ..
#mvn liberty:configure-arquillian
mvn clean -q
#mvn liberty:dev &
mvn liberty:create -q
mvn liberty:install-feature -q
mvn liberty:deploy -q
mvn liberty:start -q

mvn verify
#mvn liberty:stop