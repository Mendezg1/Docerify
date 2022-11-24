package com.example.docerify

data class UserModel (
    val userid: String? = null,
    val email: String? = null,
    val password: String? = null,
    val username: String? = null,
    var tags: MutableList<String>? = null,
    var tutor: Boolean? = null
)