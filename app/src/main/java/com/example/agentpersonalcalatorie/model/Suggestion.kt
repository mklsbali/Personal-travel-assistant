package com.example.agentpersonalcalatorie.model

import java.io.Serializable

data class Suggestion(
var id:Int,
var idTrip: Int,
var name: String,
var image: Int,
var take: Boolean
): Serializable