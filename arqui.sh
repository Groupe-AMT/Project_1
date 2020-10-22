#!/bin/bash
mvn liberty:stop

mvn clean
mvn liberty:dev &
mvn liberty:create
mvn liberty:install-feature
mvn liberty:deploy
mvn liberty:start
#mvn liberty:configure-arquillian

mvn verify
#mvn liberty:stop