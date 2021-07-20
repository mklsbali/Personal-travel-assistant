package com.example.agentpersonalcalatorie.restaurantadapter

import java.io.Serializable

data class MenuItem(
    var title: String,
    var image: Int,
    var price: Int,
    var isSelected:Boolean
):Serializable