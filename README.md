## Run the following commands

docker build -t kafka-connect-spooldir-poc .

docker-compose up -d

curl -d @kafka/source/connect-file-source.json -H "Content-Type: application/json" -X POST http://localhost:8083/connectors
