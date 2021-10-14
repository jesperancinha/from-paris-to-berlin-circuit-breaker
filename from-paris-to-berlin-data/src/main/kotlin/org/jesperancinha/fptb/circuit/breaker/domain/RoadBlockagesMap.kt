package org.jesperancinha.fptb.circuit.breaker.domain

import org.jesperancinha.fptb.circuit.breaker.dto.Location
import org.springframework.stereotype.Component

/**
 * Created by jofisaes on 14/10/2021
 */
@Component
data class RoadBlockagesMap(
    var location: Location? = null
)