## Run the following commands

`docker build -t kafka-connect-spooldir-poc .`

`docker-compose up -d`

#### Create folders
When you start the connector, it will immediately read the CSV file. There is a copy in the `original-source` folder.
It puts errors / finished files in the corresponding subfolder so you need to make them by running these two:

`mkdir -p kafka/source/errors`

`mkdir -p kafka/source/finished`

## Create topic
kafka-topics --bootstrap-server localhost:9092  --create --topic radius-accounting --partitions 1

## Managing connectors

#### Create
`curl -d @kafka/source/connect-file-source.json -H "Content-Type: application/json" -X POST http://localhost:8083/connectors`
#### List
`curl http://localhost:8083/connectors`
#### Delete
`curl -X DELETE http://localhost:8083/connectors/radius-accounting-source-json`