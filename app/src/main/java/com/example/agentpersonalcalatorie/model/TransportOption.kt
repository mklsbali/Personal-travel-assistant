package com.example.agentpersonalcalatorie.model
import java.io.Serializable

data class TransportOption (
    var categorie: String,
    var timp: String,
    var km: Double,
    var descriere: String,
    var linie: String, // Bus
    var caloriiArse: Double, // Walk
    var pretAprox: Double, // Taxi
    var numarMasina: String, // Taxi
    var imagine: Int

) : Serializable