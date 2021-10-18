package org.jesperancinha.fptb.circuit.breaker.domain

enum class BlockageType {
    TIMEOUT,
    ERROR,
    HALF_PERCENT_FAIL,
    NONE,
    UNKNOWN
}