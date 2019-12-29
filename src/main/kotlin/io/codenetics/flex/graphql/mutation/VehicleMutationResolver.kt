package io.codenetics.flex.graphql.mutation

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import graphql.schema.DataFetchingEnvironment
import io.codenetics.flex.domain.vehicle.Drivetrain
import io.codenetics.flex.domain.vehicle.EngineType
import io.codenetics.flex.domain.vehicle.Transmission
import io.codenetics.flex.domain.vehicle.Vehicle
import io.codenetics.flex.exception.GraphQLRequestError
import io.codenetics.flex.graphql.context.AuthContext
import io.codenetics.flex.service.VehicleService
import io.codenetics.flex.service.VehicleSpecService
import org.springframework.stereotype.Service


/**
 *  Created by Pavel Laktiushkin on 14.03.2019
 */
@Service
class VehicleMutationResolver(val vehicleService: VehicleService,
                              val vehicleSpecService: VehicleSpecService) : GraphQLMutationResolver {


    fun createVehicle(name: String?, description: String?, year: Int, ownedSince: Int, ownedTo: Int?,
                      generationId: String, displacement: Float?, engineType: EngineType?, transmission: Transmission?, drivetrain: Drivetrain?,
                      horsepower: Int?, env: DataFetchingEnvironment): String? {
        val user = env.getContext<AuthContext>().user ?: throw GraphQLRequestError("Unauthorized")

        // Validations
        val generation = vehicleSpecService.getGeneration(generationId) ?: throw GraphQLRequestError("Vehicle generation not found by id")
        val vehicle = Vehicle(owner = user, name = name, description = description, year = year, ownedSince = ownedSince, ownedTo = ownedTo,
                model = generation.model, generation = generation, displacement = displacement, engineType = engineType, transmission = transmission,
                driveTrain = drivetrain, horsepower = horsepower)

        val result = vehicleService.persistVehicle(vehicle)

        return result.id
    }


}