package com.example.messenger.ui.main.models

data class User(
    val id: String = "",
    var username: String = "",
    var bio: String = "",
    var firstname: String = "",
    var lastname: String = "",
    var status: String = "",
    var phone: String = "",
    var photoUrl: String = ""
)