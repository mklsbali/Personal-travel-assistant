package com.example.agentpersonalcalatorie.obiective_turistice_adapter

import java.io.Serializable

data class Objective(
    var title: String,
    var image: Int,
    var description: String,
    var isSelected:Boolean
): Serializable