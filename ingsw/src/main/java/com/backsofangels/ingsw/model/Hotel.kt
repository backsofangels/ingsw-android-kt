package com.backsofangels.ingsw.model


class Hotel(id: Long, name: String, nation: String, town: String, road: String, houseNumber: String, phoneNumber: String, priceRange: Int,
            var stars: Int, var owner: String, var isFull: Boolean, var priceSinglePerson: Double, var services: Set<String>) : Structure(id, name, nation, town,
        road, houseNumber, phoneNumber, priceRange)
