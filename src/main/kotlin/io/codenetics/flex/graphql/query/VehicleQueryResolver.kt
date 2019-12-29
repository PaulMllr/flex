package io.codenetics.flex.graphql.query

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import graphql.schema.DataFetchingEnvironment
import io.codenetics.flex.domain.vehicle.Vehicle
import io.codenetics.flex.exception.GraphQLRequestError
import io.codenetics.flex.graphql.context.AuthContext
import io.codenetics.flex.graphql.dto.UserDto
import io.codenetics.flex.graphql.dto.VehicleDto
import io.codenetics.flex.service.VehicleService
import org.springframework.stereotype.Service

/**
 *  Created by Pavel Laktiushkin on 14.03.2019
 */
@Service
class VehicleQueryResolver(val vehicleService: VehicleService) : GraphQLQueryResolver {


    fun getOwnVehicles(env: DataFetchingEnvironment): List<VehicleDto> {
        val user = env.getContext<AuthContext>().user ?: throw GraphQLRequestError("Unauthorized")

        return vehicleService.getVehiclesByOwner(user).map { vehicleToDto(it) }.toList()
    }

    fun getVehicle(vehicleId: String): VehicleDto {
        val vehicle = getVehicleOrThrowNotFound(vehicleId)
        return vehicleToDto(vehicle)
    }




    private fun vehicleToDto(vehicle: Vehicle): VehicleDto = VehicleDto(vehicle.id, vehicle.createdAt, vehicle.name,
            vehicle.description, vehicle.owner.fullName, vehicle.year, vehicle.ownedSince, vehicle.ownedTo, vehicle.model.market,
            vehicle.model.manufacturer.name, vehicle.model.name, vehicle.generation.name,
            vehicle.displacement, vehicle.engineType, vehicle.transmission,
            vehicle.driveTrain, vehicle.horsepower)

    private fun getVehicleOrThrowNotFound(vehicleId: String) =
            vehicleService.getVehicleById(vehicleId) ?: throw GraphQLRequestError("Vehicle not found by id")


}