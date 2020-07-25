package com.backsofangels.ingsw.model

class UserDto {
    private lateinit var email: String
    private lateinit var username: String
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var password: String

    constructor() {}

    constructor(email: String, username: String, firstName: String, lastName: String, password: String) {
        this.email = email
        this.username = username
        this.firstName = firstName
        this.lastName = lastName
        this.password = password
    }
}