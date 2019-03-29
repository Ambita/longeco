package com.ambita.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper, SerializationFeature}

object ObjectMapperFactory {
  def create(): ObjectMapper = {
    configure(new ObjectMapper())
  }
  def configure(objectMapper: ObjectMapper): ObjectMapper = {
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT)
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
  }
}