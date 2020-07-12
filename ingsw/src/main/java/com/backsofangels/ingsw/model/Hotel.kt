package com.backsofangels.ingsw.model


class Hotel(id: Long, name: String, nation: String, town: String, road: String, houseNumber: String, phoneNumber: String, priceRange: Int, averageScore: Double,
            var stars: Int, var owner: String, var isFull: Boolean, var priceSinglePerson: Double, var services: Set<String>) : Structure(id, name, nation, town,
        road, houseNumber, phoneNumber, priceRange, averageScore) {
    override fun toString(): String {
        return "Hotel(" +
                "stars=$stars, " +
                "owner='$owner', " +
                "isFull=$isFull, " +
                "priceSinglePerson=$priceSinglePerson, " +
                "services=$services" +
                ")" +
                " ${super.toString()}"
    }
}
