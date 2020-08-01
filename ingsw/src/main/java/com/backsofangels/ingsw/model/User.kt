package com.backsofangels.ingsw.model

class User {
    private var id: Long
    private var email: String
    private var username: String
    private var firstName: String
    private var lastName: String

    private val defaultStringValue = "default"

    init {
        id = Long.MIN_VALUE
        email = defaultStringValue
        username = defaultStringValue
        firstName = defaultStringValue
        lastName = defaultStringValue
    }

    constructor() {}

    constructor(id: Long, email: String, username: String, firstName: String, lastName: String) {
        this.id = id
        this.email = email
        this.username = username
        this.firstName = firstName
        this.lastName = lastName
    }
}
