package com.giffgaff.radius.accounting.processing;

import com.giffgaff.radius.accounting.serialisation.schema.AccountingRecord;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ByteCountProcessor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    private static final Serde<String> STRING_SERDE = Serdes.String();

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        KStream<String, AccountingRecord> messageStream = streamsBuilder
            .stream("radius-accounting"
//                , Consumed.with(STRING_SERDE, STRING_SERDE)
            );

        KTable<String, Long> totalBytesDownloaded = messageStream
            .selectKey((oldKey, accRecord) -> accRecord.getUsername())
            .mapValues(accRecord -> accRecord.getPacketsDownloaded() / 4)
            .groupByKey()
            .count(Materialized.as("totalBytesDownloaded"));

        totalBytesDownloaded.toStream()
            .peek((k, v) -> logger.info("User {} downloaded {} bytes", k, v))
            .to("total-bytes-downloaded");
    }
}
