mvn clean package docker:build -DskipTests
docker images --all
docker run hands-on-spring-packt-docker-image
docker ps
docker exec -it adf01dd5c67b curl http://localhost:8080/application/health