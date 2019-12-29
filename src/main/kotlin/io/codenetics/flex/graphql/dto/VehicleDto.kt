package io.codenetics.flex.graphql.dto

import io.codenetics.flex.domain.vehicle.Drivetrain
import io.codenetics.flex.domain.vehicle.EngineType
import io.codenetics.flex.domain.vehicle.Market
import io.codenetics.flex.domain.vehicle.Transmission
import java.time.Instant

/**
 *  Created by Pavel Laktiushkin on 28.09.2019
 */
data class VehicleDto(var id: String,
                      var createdAt: Instant,
                      var name: String?,
                      var description: String?,
                      var owner: String,
                      var year: Int,
                      var ownedSince: Int,
                      var ownedTo: Int?,
                      var market: Market?,
                      var manufacturer: String,
                      var model: String,
                      var generation: String,
                      var displacement: Float?,
                      var engineType: EngineType?,
                      var transmission: Transmission?,
                      var drivetrain: Drivetrain?,
                      var horsepower: Int?)