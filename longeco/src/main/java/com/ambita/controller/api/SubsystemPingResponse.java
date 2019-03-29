package com.ambita.controller.api;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import static com.ambita.controller.api.PingResponse.API_DATE_TIME_FORMAT;

@Setter
@Getter
@JsonPropertyOrder(value = {"available", "component", "required", "latency", "message", "uptodateTime"})
public class SubsystemPingResponse {

  protected boolean required;
  protected String component;
  protected boolean available;
  protected String latency;
  protected String message;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = API_DATE_TIME_FORMAT)
  @JsonProperty("uptodate_time")
  protected LocalDateTime uptodateTime;
}
