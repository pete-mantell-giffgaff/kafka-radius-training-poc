package com.giffgaff.radius.accounting.serialisation.serde;

import com.fasterxml.jackson.databind.JsonNode;
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

      JsonNode tree = mapper.readTree(data);
      JsonNode payload = tree.get("payload");

      AccountingRecord accountingRecord = mapper.readValue(payload.traverse(), AccountingRecord.class);

      logger.debug("Successfully deserialised record {}", accountingRecord);

      return accountingRecord;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
