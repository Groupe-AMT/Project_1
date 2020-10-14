#!/bin/bash

cd ..
mvn clean package
sudo docker build -t open-affect/projet1 .
