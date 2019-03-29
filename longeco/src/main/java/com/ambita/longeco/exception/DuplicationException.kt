package com.ambita.longeco.exception

data class DuplicationException(val msg: String) : RuntimeException(msg)
