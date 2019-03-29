package com.ambita.util

import org.slf4j.{Logger, LoggerFactory}

trait Slf4jLogger {
  def logger: Logger = LoggerFactory.getLogger(this.getClass)
}
