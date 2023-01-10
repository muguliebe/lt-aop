package com.example.fwk.custom.pojo

data class CommonsUser(
    var userId: Int = 0,
    var email: String = "",
    var jwt: String = "",
    val level: Int = 0,
    val isDeny: String = "",
)
