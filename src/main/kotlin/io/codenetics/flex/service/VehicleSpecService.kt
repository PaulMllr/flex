package io.codenetics.flex.service

import io.codenetics.flex.dao.GenerationDao
import io.codenetics.flex.dao.ManufacturerDao
import io.codenetics.flex.dao.ModelDao
import io.codenetics.flex.domain.vehicle.Generation
import io.codenetics.flex.domain.vehicle.Manufacturer
import io.codenetics.flex.domain.vehicle.Market
import io.codenetics.flex.domain.vehicle.Model
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

/**
 *  Created by Pavel Laktiushkin on 10.10.2019
 */
@Service
class VehicleSpecService(val manufacturerDao: ManufacturerDao, private val modelDao: ModelDao, val generationDao: GenerationDao) {

    fun getManufacturer(id: String): Manufacturer? {
        return manufacturerDao.findByIdOrNull(id)
    }

    fun getAllManufacturers(): MutableIterable<Manufacturer> {
        return manufacturerDao.findAll()
    }

    fun getModel(manufacturer: Manufacturer, id: String): Model? {
        return modelDao.findFirstByIdAndManufacturer(id, manufacturer)
    }

    fun getModel(id: String): Model? {
        return modelDao.findByIdOrNull(id)
    }

    fun getGeneration(id: String): Generation? {
        return generationDao.findByIdOrNull(id)
    }

    fun getModels(manufacturer: Manufacturer, market: Market?): List<Model> {
        return if (market == null) {
            modelDao.findAllByManufacturer(manufacturer)
        } else {
            modelDao.findAllByManufacturerAndMarket(manufacturer, market)
        }
    }

    fun getModels(manufacturerId: String, market: Market?): List<Model> {
        return if (market == null) {
            modelDao.findAllByManufacturerId(manufacturerId)
        } else {
            modelDao.findAllByManufacturerIdAndMarket(manufacturerId, market)
        }
    }

    fun getAllGenerations(model: Model): List<Generation> {
        return generationDao.findAllByModel(model)
    }

    fun getAllGenerations(modelId: String): List<Generation> {
        return generationDao.findAllByModelId(modelId)
    }

}