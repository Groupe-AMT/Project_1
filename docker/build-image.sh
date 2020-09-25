#mvn clean install cleans the project and turns it into a .war or .jar depending on pom.xml
sudo docker rmi -f $(sudo docker images -a -q)
cd ../target
rm $(ls -a ./ | grep *'war')
cd ../docker
rm $(ls -a ./ | grep *'war')
cd ..
mvn clean install
cd ./target
cp ./*.war ../docker/
cd ../docker
sudo docker-compose up

