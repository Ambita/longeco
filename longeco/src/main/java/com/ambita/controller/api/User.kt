package com.ambita.controller.api

import java.util.*

data class User
(
        val id: Optional<Long>,
        val username: String,
        val password: String,
        val email: String,
        val name: String,
        val active: Boolean = true
)
