package com.giffgaff.radius.accounting.serialisation.serde;

import com.giffgaff.radius.accounting.serialisation.schema.AccountingRecord;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class AccountingRecordSerde implements Serde<AccountingRecord> {
  @Override
  public Serializer<AccountingRecord> serializer() {
    return new AccountingRecordSerialiser();
  }

  @Override
  public Deserializer<AccountingRecord> deserializer() {
    return new AccountingRecordDeserialiser();
  }
}
