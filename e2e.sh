#!/bin/bash

cd e2e
npm install

HTTP="http://"
ADRESS=`docker inspect -f \'{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}\' $(docker ps -aqf 'name=docker_openliberty')`
FullAdress="$HTTP${ADRESS//\'}"
OverRide='{ "helpers": {"Puppeteer": {"url": "'$FullAdress'"}}}'
echo $OverRide
npx codeceptjs init
npx codeceptjs run Projet1_test.js --override "$OverRide"
