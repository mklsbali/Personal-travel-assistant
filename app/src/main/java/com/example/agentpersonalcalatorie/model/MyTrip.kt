package com.example.agentpersonalcalatorie.model

import java.io.Serializable

data class MyTrip(
    var id: Int,
    var title: String,
    var image: Int,
    var price: Int,
    var description: String,
    var date: String
):Serializable