package com.backsofangels.ingsw.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties("id")
class Review {
    var userId: Int
    var structureId: Int
    var title: String
    var username: String
    var rating: Int
    var description: String

    init {
        userId = 0
        structureId = 0
        username = ""
        title = "test"
        rating = Int.MIN_VALUE
        description = "default"
    }

    constructor()

    constructor(userId: Int, structureId: Int, title: String, username: String, rating: Int, description: String) {
        this.userId = userId
        this.structureId = structureId
        this.username = username
        this.title = title
        this.rating = rating
        this.description = description
    }

}