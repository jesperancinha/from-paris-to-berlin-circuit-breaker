package org.jesperancinha.fptb.circuit.breaker.domain

enum class BlockageType {
    TIMEOUT,
    ERROR,
    NONE,
    UNKNOWN
}