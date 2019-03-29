package com.ambita.config

import com.ambita.util.Slf4jLogger
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Component
@Primary
class ObjectMapperFactoryBean() extends ObjectMapper with Slf4jLogger{
  logger.info("Configuring jackson object mapper")
  ObjectMapperFactory.configure(this)
}