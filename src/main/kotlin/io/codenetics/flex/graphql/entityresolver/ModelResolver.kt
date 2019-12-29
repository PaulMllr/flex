package io.codenetics.flex.graphql.entityresolver

import com.coxautodev.graphql.tools.GraphQLResolver
import io.codenetics.flex.graphql.dto.GenerationDto
import io.codenetics.flex.graphql.dto.ModelDto
import io.codenetics.flex.service.VehicleSpecService
import org.springframework.stereotype.Component


/**
 *  Created by Pavel Laktiushkin on 28.09.2019
 */
@Component
class ModelResolver(private val vehicleSpecService: VehicleSpecService) : GraphQLResolver<ModelDto> {
    fun generations(model: ModelDto): List<GenerationDto> {
        return vehicleSpecService.getAllGenerations(model.id).map { GenerationDto(it.id, it.name, it.yearStart, it.yearEnd) }
    }
}