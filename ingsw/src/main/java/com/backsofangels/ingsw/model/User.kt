package com.backsofangels.ingsw.model

class User {
    var id: Long? = null
    var email: String? = null
    var username: String? = null
    var firstName: String? = null
    var lastName: String? = null

    constructor() {}

    constructor(id: Long?, email: String?, username: String?, firstName: String?, lastName: String?) {
        this.id = id
        this.email = email
        this.username = username
        this.firstName = firstName
        this.lastName = lastName
    }
}
