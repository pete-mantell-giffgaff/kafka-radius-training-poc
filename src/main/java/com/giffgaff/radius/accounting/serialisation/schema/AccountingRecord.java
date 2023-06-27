package com.giffgaff.radius.accounting.serialisation.schema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountingRecord {
  private String eventTimestamp;
  private String username;
  private Long packetsDownloaded;
  private Long packetsUploaded;

  public String getEventTimestamp() {
    return eventTimestamp;
  }

  public void setEventTimestamp(String eventTimestamp) {
    this.eventTimestamp = eventTimestamp;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Long getPacketsDownloaded() {
    return packetsDownloaded;
  }

  public void setPacketsDownloaded(Long packetsDownloaded) {
    this.packetsDownloaded = packetsDownloaded;
  }

  public Long getPacketsUploaded() {
    return packetsUploaded;
  }

  public void setPacketsUploaded(Long packetsUploaded) {
    this.packetsUploaded = packetsUploaded;
  }
}
