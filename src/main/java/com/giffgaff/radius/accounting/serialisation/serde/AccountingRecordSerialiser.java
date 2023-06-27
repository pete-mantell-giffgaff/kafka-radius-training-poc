package com.giffgaff.radius.accounting.serialisation.serde;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.giffgaff.radius.accounting.serialisation.schema.AccountingRecord;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountingRecordSerialiser implements Serializer<AccountingRecord> {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final ObjectMapper mapper = new ObjectMapper();

  @Override
  public byte[] serialize(String topic, AccountingRecord data) {
    try {
      String asString = mapper.writeValueAsString(data);
      logger.info("Serialising {} to {}", asString, topic);
      return asString.getBytes();
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
