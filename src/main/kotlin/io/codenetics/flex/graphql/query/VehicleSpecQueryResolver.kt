package io.codenetics.flex.graphql.query

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import graphql.schema.DataFetchingEnvironment
import io.codenetics.flex.domain.vehicle.Market
import io.codenetics.flex.exception.GraphQLRequestError
import io.codenetics.flex.graphql.dto.GenerationDto
import io.codenetics.flex.graphql.dto.ManufacturerDto
import io.codenetics.flex.graphql.dto.ModelDto
import io.codenetics.flex.service.VehicleSpecService
import org.springframework.stereotype.Service

/**
 *  Created by Pavel Laktiushkin on 14.03.2019
 */
@Service
class VehicleSpecQueryResolver(val vehicleSpecService: VehicleSpecService) : GraphQLQueryResolver {


    fun getManufacturers(env: DataFetchingEnvironment): List<ManufacturerDto> {
        return vehicleSpecService.getAllManufacturers().map { ManufacturerDto(it.id, it.name, emptyList()) }
    }

    fun getModels(manufacturerId: String, market: Market?, env: DataFetchingEnvironment): List<ModelDto> {
        val manufacturer = vehicleSpecService.getManufacturer(manufacturerId) ?: throw GraphQLRequestError("Manufacturer not found by id")
        return vehicleSpecService.getModels(manufacturer, market).map { ModelDto(it.id, it.name, it.market, emptyList()) }
    }

    fun getGenerations(modelId: String, env: DataFetchingEnvironment): List<GenerationDto> {
        val model = vehicleSpecService.getModel(modelId) ?: throw GraphQLRequestError("Model not found by id")
        return vehicleSpecService.getAllGenerations(model).map { GenerationDto(it.id, it.name, it.yearStart, it.yearEnd) }
    }

}