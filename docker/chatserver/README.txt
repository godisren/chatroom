# build docker image
docker build -t chatroom-server-queue .

# run docker container
docker run -id -p 8080:8080 chatroom-server-queue