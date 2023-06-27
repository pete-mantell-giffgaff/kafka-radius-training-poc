FROM confluentinc/cp-server-connect-base:7.3.2

RUN   confluent-hub install --no-prompt jcustenborder/kafka-connect-spooldir:latest