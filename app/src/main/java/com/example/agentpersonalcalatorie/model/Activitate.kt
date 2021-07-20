package com.example.agentpersonalcalatorie.model

import java.io.Serializable

data class Activitate(
    var id: Int,
    var title: String,
    var date: String,
    var text: String,
    var hour: String,
    var tripId: Int
):Serializable

