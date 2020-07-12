package com.backsofangels.ingsw.model

import java.time.LocalTime
import java.util.*


class Restaurant(id: Long, name: String, nation: String, town: String, road: String, houseNumber: String, phoneNumber: String, priceRange: Int, averageScore: Double,
                 var michelinRating: Int, var openingTime: LocalTime, var closingTime: LocalTime, var cookingTypes: Set<String>) : Structure(id, name, nation, town, road, houseNumber, phoneNumber, priceRange, averageScore) {
    override fun toString(): String {
        return "Restaurant(" +
                "michelinRating=$michelinRating, " +
                "openingTime=$openingTime, " +
                "closingTime=$closingTime, " +
                "cookingTypes=$cookingTypes" +
                ")" +
                " ${super.toString()}"
    }
}
