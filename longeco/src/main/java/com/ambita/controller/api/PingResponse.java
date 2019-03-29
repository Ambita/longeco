package com.ambita.controller.api;


import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

@Setter
@Getter
@JsonPropertyOrder(value = {"online", "statusTime", "system", "version", "env", "releasedTime", "startedTime", "latency", "details"})
public class PingResponse {
  public static final String API_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";

  boolean online;
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = API_DATE_TIME_FORMAT)
  @JsonProperty("status_time")
  LocalDateTime statusTime;
  @JsonProperty("system")
  String systemName;
  String version;
  String env;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = API_DATE_TIME_FORMAT)
  @JsonProperty("release_time")
  LocalDateTime releasedTime;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = API_DATE_TIME_FORMAT)
  @JsonProperty("started_time")
  LocalDateTime startedTime;
  String latency;

  List<SubsystemPingResponse> details = newArrayList();

  public void addResourcePing(SubsystemPingResponse resourcePing) {
    details.add(resourcePing);
  }
}
