package com.backsofangels.ingsw.model

import java.time.LocalTime
import java.util.*


class Restaurant(id: Long, name: String, nation: String, town: String, road: String, houseNumber: String, phoneNumber: String, priceRange: Int, michelinRating: Int, openingTime: LocalTime, closingTime: LocalTime, cookingTypes: Set<String>) : Structure(id, name, nation, town, road, houseNumber, phoneNumber, priceRange) {
    /*
    private var michelinRating: Int
        get() = michelinRating
        set(value) {michelinRating = value}
    private var openingTime: LocalTime
        get() = openingTime
        set(value) {openingTime = value}
    private var closingTime: LocalTime
        get () = closingTime
        set(value) {closingTime = value}
    private var cookingTypes: Set<String>
        get() = cookingTypes
        set(value) {cookingTypes = value}

    init {
        this.michelinRating = michelinRating
        this.openingTime = openingTime
        this.closingTime = closingTime
        this.cookingTypes = cookingTypes
    }
     */
}
