package com.backsofangels.ingsw.model

import com.backsofangels.ingsw.utils.LocalTimeDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.time.LocalTime
import java.util.*


class Restaurant: Structure{
    var michelinRating: Int

    @JsonDeserialize(using = LocalTimeDeserializer::class)
    var openingTime: LocalTime

    @JsonDeserialize(using = LocalTimeDeserializer::class)
    var closingTime: LocalTime
    var cookingTypes: Set<String>

    init {
        michelinRating = Int.MIN_VALUE
        openingTime = LocalTime.MIN
        closingTime = LocalTime.MIN
        cookingTypes = emptySet()
    }

    constructor()

    constructor(id: Long, name: String, nation: String, town: String, road: String, houseNumber: String, phoneNumber: String, priceRange: Int, averageScore: Double, imageSource: String,
                michelinRating: Int, openingTime: LocalTime, closingTime: LocalTime, cookingTypes: Set<String>): super(id, name, nation, town, road, houseNumber, phoneNumber, priceRange, averageScore, imageSource) {
        this.michelinRating = michelinRating
        this.openingTime = openingTime
        this.closingTime = closingTime
        this.cookingTypes = cookingTypes
    }

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
