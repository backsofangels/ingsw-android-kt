package com.backsofangels.ingsw.model


class Hotel: Structure {
    var stars: Int
    var owner: String
    var isFull: Boolean
    var priceSinglePerson: Double
    var services: Set<String>

    init {
        stars = Int.MIN_VALUE
        owner = super.defaultValue
        isFull = true
        priceSinglePerson = Double.MIN_VALUE
        services = emptySet()
    }

    constructor()

    constructor(id: Long, name: String, nation: String, town: String, road: String, houseNumber: String, phoneNumber: String, priceRange: Int, averageScore: Double, imageSource: String,
                stars: Int, owner: String, isFull: Boolean, priceSinglePerson: Double, services: Set<String>): super(id, name, nation, town, road, houseNumber, phoneNumber, priceRange, averageScore, imageSource) {
        this.stars = stars
        this.owner = owner
        this.isFull = isFull
        this.priceSinglePerson = priceSinglePerson
        this.services = services
    }


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
