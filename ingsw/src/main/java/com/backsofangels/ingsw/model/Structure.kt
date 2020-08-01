package com.backsofangels.ingsw.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
open class Structure {
    var id: Long
    var name: String
    var nation: String
    var town: String
    var road: String
    var houseNumber: String
    var phoneNumber: String
    var priceRange: Int
    var averageScore: Double
    var imageSource: String

    val defaultValue = "Default"

    init {
        id = Long.MIN_VALUE
        name = defaultValue
        nation = defaultValue
        town = defaultValue
        road = defaultValue
        houseNumber = defaultValue
        phoneNumber = defaultValue
        priceRange = Int.MIN_VALUE
        averageScore = Double.MIN_VALUE
        imageSource = defaultValue
    }

    constructor()

    constructor(id: Long, name: String, nation: String, town: String, road: String, houseNumber: String, phoneNumber: String, priceRange: Int, averageScore: Double, imageSource: String) {
        this.id = id
        this.name = name
        this.nation = nation
        this.town = town
        this.road = road
        this.houseNumber = houseNumber
        this.phoneNumber = phoneNumber
        this.priceRange = priceRange
        this.averageScore = averageScore
        this.imageSource = imageSource
    }

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