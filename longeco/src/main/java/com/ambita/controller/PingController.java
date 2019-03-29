package com.ambita.controller;

import java.time.LocalDateTime;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ambita.controller.api.PingProperties;
import com.ambita.controller.api.PingResponse;
import com.ambita.controller.api.SubsystemPingResponse;
import com.ambita.repository.EventRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class PingController {
  private static final Logger LOG = LoggerFactory.getLogger(PingController.class);
  private final LocalDateTime startDate = LocalDateTime.now();

  private final PingProperties pingProperties;

  private final EventRepository eventRepository;

  private SubsystemPingResponse lastPingFromDatabase;

  @Autowired
  public PingController(PingProperties pingProperties, EventRepository eventRepository) {
    this.pingProperties = pingProperties;
    this.eventRepository = eventRepository;
  }

  @GetMapping(value = "/systemping", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<PingResponse> systemping() {
    return pingInternal();
  }

  private ResponseEntity<PingResponse> pingInternal() {
    long start = System.nanoTime();
    PingResponse pingResponse = new PingResponse();
    pingResponse.setStatusTime(LocalDateTime.now());
    pingResponse.setStartedTime(startDate);

    pingResponse.setEnv(pingProperties.getEnv());
    pingResponse.setSystemName(pingProperties.getName());

    pingResponse.addResourcePing(getLastPingFromDB());
    pingResponse.setLatency(parseLatency(start));
    pingResponse.setOnline(isOnline( pingResponse.getDetails()));
    if (!pingResponse.isOnline()) {
      return new ResponseEntity<>(pingResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }
    return ResponseEntity.ok(pingResponse);
  }

  @Scheduled(fixedRateString = "${ping.fixed-rate}")
  public void pingDatabase() {
    LOG.info("ping database");
    long start = System.nanoTime();
    SubsystemPingResponse resource = createPingResponseDefault("DATABASE", true);
    try {
      eventRepository.pingDatabase();
      resource.setAvailable(true);
      resource.setMessage("OK");
      resource.setLatency(parseLatency(start));
    }
    catch (Exception e) {
      resource.setMessage(e.getMessage());
      resource.setAvailable(false);
      resource.setLatency(parseLatency(start));
    }
    lastPingFromDatabase = resource;
  }

  private static String parseLatency(long start) {
    return (System.nanoTime() - start) / 1000000L + "ms";
  }

  private boolean isOnline(Collection<SubsystemPingResponse> resources) {
    return resources.stream().noneMatch(subsystem -> subsystem.isRequired() && !subsystem.isAvailable());
  }

  private SubsystemPingResponse getLastPingFromDB() {
    if (lastPingFromDatabase == null) {
      pingDatabase();
    }
    return lastPingFromDatabase;
  }

  private SubsystemPingResponse createPingResponseDefault(String component, boolean required) {
    SubsystemPingResponse resource = new SubsystemPingResponse();
    resource.setUptodateTime(LocalDateTime.now());
    resource.setComponent(component);
    resource.setRequired(required);
    return resource;
  }

}
