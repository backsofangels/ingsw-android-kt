package com.backsofangels.ingsw.model

class TouristAttraction: Structure {
    var ageLimit: Int
    var peopleLimit: Int
    var isFull: Boolean
    var services: Set<String>

    init {
        ageLimit = Int.MIN_VALUE
        peopleLimit = Int.MIN_VALUE
        isFull = false
        services = emptySet()
    }

    constructor()

    constructor(id: Long, name: String, nation: String, town: String, road: String, houseNumber: String, phoneNumber: String, priceRange: Int, averageScore: Double, imageSource: String,
                ageLimit: Int, peopleLimit: Int, isFull: Boolean, services: Set<String>): super(id, name, nation, town, road, houseNumber, phoneNumber, priceRange, averageScore, imageSource) {
        this.ageLimit = ageLimit
        this.peopleLimit = peopleLimit
        this.isFull = isFull
        this.services = services
    }

    override fun toString(): String {
        return "TouristAttraction(" +
                "ageLimit=$ageLimit, " +
                "peopleLimit=$peopleLimit, " +
                "isFull=$isFull, " +
                "services=$services" +
                ")" +
                " ${super.toString()}"
    }
}