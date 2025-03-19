package com.example.asteroidradar.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Asteroid (
    val name: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val diameter: Float,
    val relativeVelocity: String,
    val distance: String,
    val image: String,
    val isPotentiallyHazardous: Boolean
): Parcelable