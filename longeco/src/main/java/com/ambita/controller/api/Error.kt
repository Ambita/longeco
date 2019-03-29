package com.ambita.controller.api

import com.fasterxml.jackson.annotation.JsonProperty

data class Error
(
        @JsonProperty(required = true) val code: Int,
        @JsonProperty(required = true) val message: String
)
