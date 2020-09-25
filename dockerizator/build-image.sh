#mvn clean install cleans the project and turns it into a .war or .jar depending on pom.xml 
cd ..
mvn clean install
cd ./target
cp ./*.war ../dockerizator/
cd ../dockerizator
sudo docker-compose up

