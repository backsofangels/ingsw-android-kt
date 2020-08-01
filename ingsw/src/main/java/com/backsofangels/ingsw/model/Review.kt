package com.backsofangels.ingsw.model

class Review {
    var user: User
    var structure: Structure
    var rating: Int
    var description: String

    init {
        user = User()
        structure = Structure()
        rating = Int.MIN_VALUE
        description = "default"
    }

    constructor()

    constructor(user: User, structure: Structure, rating: Int, description: String) {
        this.user = user
        this.structure = structure
        this.rating = rating
        this.description = description
    }

}