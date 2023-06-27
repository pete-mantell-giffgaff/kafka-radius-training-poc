package com.giffgaff.radius.accounting.serialisation.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.giffgaff.radius.accounting.serialisation.schema.AccountingRecord;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AccountingRecordDeserialiser implements Deserializer<AccountingRecord> {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final ObjectMapper mapper = new ObjectMapper();
  @Override
  public AccountingRecord deserialize(String topic, byte[] data) {
    try {
      logger.info("Deserialising {} from {}", new String(data), topic);
      return mapper.readValue(data, AccountingRecord.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public AccountingRecord deserialize(String topic, Headers headers, byte[] data) {
    return Deserializer.super.deserialize(topic, headers, data);
  }
}
