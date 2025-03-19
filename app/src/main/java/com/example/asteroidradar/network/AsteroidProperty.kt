package com.example.asteroidradar.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AsteroidProperty(
    val id: String,
    val name: String,
    @SerialName("close_approach_data") val closeApproachData: List<ApproachDate>,
    @SerialName("absolute_magnitude_h")  val absoluteMagnitude: Double,
    @SerialName("estimated_diameter") val diameter: AsteroidDiameter,
    @SerialName("is_potentially_hazardous_asteroid") val isPotentiallyHazardous: Boolean
)
@Serializable
data class AsteroidDiameter(
    @SerialName("kilometers") val kilometers: AsteroidKilometers

)
@Serializable
data class AsteroidDistance(
    @SerialName("astronomical") val kilometers: String

)
@Serializable
data class AsteroidKilometers(
    @SerialName("estimated_diameter_min") val estimatedDiameterMin: Float,
    @SerialName("estimated_diameter_max") val estimatedDiameterMax: Float

)
@Serializable
data class RelativeVelocity(
    @SerialName("kilometers_per_second") val kilometersPerSecond: Float

)
@Serializable
data class ApproachDate(
    @SerialName("close_approach_date") val closeApproachData: String,
    @SerialName("relative_velocity") val relativeVelocity: RelativeVelocity?,
    @SerialName("miss_distance") val distance: AsteroidDistance? = null
)