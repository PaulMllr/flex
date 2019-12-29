package io.codenetics.flex.graphql.entityresolver

import com.coxautodev.graphql.tools.GraphQLResolver
import io.codenetics.flex.graphql.dto.ManufacturerDto
import io.codenetics.flex.graphql.dto.ModelDto
import io.codenetics.flex.service.VehicleSpecService
import org.springframework.stereotype.Component


/**
 *  Created by Pavel Laktiushkin on 28.09.2019
 */
@Component
class ManufacturerResolver(private val vehicleSpecService: VehicleSpecService) : GraphQLResolver<ManufacturerDto> {
    fun models(manufacturer: ManufacturerDto): List<ModelDto> {
        return vehicleSpecService.getModels(manufacturer.id, null).map { ModelDto(it.id, it.name, it.market, emptyList()) }
    }
}