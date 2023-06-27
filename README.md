# Goal
Create a Kafka streams based solution to aggregate accounting usage data from a CSV file ...

...which loosely resembles the logs from the Radius server.

### Assumptions
- Radius outputs accounting lines to a file
- Each network packet is 4,096 bits
- Pretend in data for now that the router reports every 10 seconds
- Seems to be in CSV format e.g.

```
EventTimestamp, Username, PacketsDownloaded, PacketsUploaded
27/06/2023 11:12:12, user1, 103, 27
27/06/2023 11:12:17, user2, 33, 17
27/06/2023 11:12:22, user1, 47, 35
27/06/2023 11:12:27, user2, 23, 47
27/06/2023 11:12:32, user1, 76, 58
27/06/2023 11:12:37, user2, 12, 69
27/06/2023 11:12:42, user1, 48, 70
```

### Tasks
- Set up to import the accounting data from CSV into a topic
- Produce a total download/upload in window
- Start with every 30 seconds
- Then extend with configurable interval?
- Then add average speed

# Set up and run (away)

### Run the following commands

`docker build -t kafka-connect-spooldir-poc .`

`docker-compose up -d`

#### Create folders
When you start the connector, it will immediately read the CSV file. There is a copy in the `original-source` folder.
It puts errors / finished files in the corresponding subfolder so you need to make them by running these two:

`mkdir -p kafka/source/errors`

`mkdir -p kafka/source/finished`

### Create topics
`kafka-topics --bootstrap-server localhost:9092  --create --topic radius-accounting --partitions 1`

`kafka-topics --bootstrap-server localhost:9092  --create --topic total-bytes-downloaded --partitions 1`

### Managing connectors

#### Create
`curl -d @kafka/source/connect-file-source.json -H "Content-Type: application/json" -X POST http://localhost:8083/connectors`
#### List
`curl http://localhost:8083/connectors`
#### Delete
`curl -X DELETE http://localhost:8083/connectors/radius-accounting-source-json`
