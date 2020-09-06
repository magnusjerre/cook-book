docker run -p 5432:5432 --name cook-book-db --net cook-book-network -d cook-book-db

@echo off
ECHO Container IPAddress
docker inspect --format="{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}" cook-book-db
@echo on
