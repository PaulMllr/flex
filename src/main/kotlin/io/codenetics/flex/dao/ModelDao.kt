package io.codenetics.flex.dao

import io.codenetics.flex.domain.vehicle.Manufacturer
import io.codenetics.flex.domain.vehicle.Market
import io.codenetics.flex.domain.vehicle.Model
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.Repository
import org.springframework.transaction.annotation.Transactional

/**
 *  Created by Pavel Laktiushkin on 03.10.2019
 */
@Transactional
interface ModelDao : Repository<Model, String>, CrudRepository<Model, String> {

    fun findFirstByIdAndManufacturer(id: String, manufacturer: Manufacturer): Model?

    fun findAllByManufacturer(manufacturer: Manufacturer): List<Model>

    fun findAllByManufacturerAndMarket(manufacturer: Manufacturer, market: Market): List<Model>

    fun findAllByManufacturerId(manufacturer: String): List<Model>

    fun findAllByManufacturerIdAndMarket(manufacturer: String, market: Market): List<Model>


}