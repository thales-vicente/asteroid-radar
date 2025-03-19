package com.example.asteroidradar.network

import androidx.room.TypeConverter
import com.example.asteroidradar.database.DatabaseAsteroids
import com.example.asteroidradar.database.DiameterFromJson
import com.example.asteroidradar.database.fromJson
import com.example.asteroidradar.database.toJson
import com.example.asteroidradar.models.Asteroid
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkAsteroidContainer(val asteroids: List<NetworkAsteroid>)

@JsonClass(generateAdapter = true)
data class NetworkAsteroid(
    val id: String,
    val name: String,
    val closeApproachData: String,
    val absoluteMagnitude: Double,
    val diameter: String,
    val isPotentiallyHazardous: Boolean)

fun NetworkAsteroidContainer.asDomainModel(): List<AsteroidProperty>{
    return asteroids.map {
        AsteroidProperty(
            id = it.id,
            name = it.name,
            closeApproachData = it.closeApproachData.fromJson(),
            absoluteMagnitude = it.absoluteMagnitude,
            diameter = it.diameter.DiameterFromJson(),
            isPotentiallyHazardous = it.isPotentiallyHazardous)
    }
}

fun NetworkAsteroidContainer.asDatabaseModel(): Array<DatabaseAsteroids> {
    return asteroids.map {
        DatabaseAsteroids(
            id = it.id,
            name = it.name,
            closeApproachData = it.closeApproachData,
            absoluteMagnitude = it.absoluteMagnitude,
            diameter = it.diameter,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }.toTypedArray()

    @TypeConverter
    fun List<ApproachDate>.toJson(): String {
        val gson = Gson()
        val type = object : TypeToken<List<DatabaseAsteroids>>() {}.type
        return gson.toJson(this, type)
    }

    @TypeConverter
    fun String.fromJson(): List<ApproachDate> {
        val gson = Gson()
        val type = object : TypeToken<List<DatabaseAsteroids>>() {}.type
        return gson.fromJson(this, type)
    }

    @TypeConverter
    fun AsteroidDiameter.toJson(): String {
        val gson = Gson()
        val type = object : TypeToken<AsteroidDiameter>() {}.type
        return gson.toJson(this, type)
    }

    @TypeConverter
    fun String.DiameterFromJson(): AsteroidDiameter {
        val gson = Gson()
        val type = object : TypeToken<AsteroidDiameter>() {}.type
        return gson.fromJson(this, type)
    }
}