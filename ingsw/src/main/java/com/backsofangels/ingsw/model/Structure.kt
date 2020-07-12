package com.backsofangels.ingsw.model

open class Structure (val id: Long, val name: String, val nation: String, val town: String,
                      val road: String, val houseNumber: String, val phoneNumber: String, val priceRange: Int, val averageScore: Double) {
    override fun toString(): String {
        return "Structure(" +
                "id=$id, " +
                "name='$name', " +
                "nation='$nation', " +
                "town='$town', " +
                "road='$road', " +
                "houseNumber='$houseNumber', " +
                "phoneNumber='$phoneNumber', " +
                "priceRange=$priceRange, " +
                "averageScore=$averageScore" +
                ")"
    }
}