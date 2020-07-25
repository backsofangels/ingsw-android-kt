package com.backsofangels.ingsw.model

class User {
    private var id: Long = 0
    private lateinit var email: String
    private lateinit var username: String
    private lateinit var firstName: String
    private lateinit var lastName: String

    constructor() {}

    constructor(id: Long, email: String, username: String, firstName: String, lastName: String) {
        this.id = id
        this.email = email
        this.username = username
        this.firstName = firstName
        this.lastName = lastName
    }
}
