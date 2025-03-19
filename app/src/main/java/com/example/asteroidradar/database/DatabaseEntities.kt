package com.example.asteroidradar.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.asteroidradar.models.Asteroid
import com.example.asteroidradar.network.ApproachDate
import com.example.asteroidradar.network.AsteroidDiameter
import com.example.asteroidradar.network.AsteroidProperty
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity
data class DatabaseAsteroids constructor(
    @PrimaryKey
    val id: String,
    val name: String,
    val closeApproachData: String,
    val absoluteMagnitude: Double,
    val diameter: String,
    val isPotentiallyHazardous: Boolean
)

fun List<DatabaseAsteroids>.asDomainModel(): List<AsteroidProperty>{
    return listOf()
    map {
        AsteroidProperty(
            id = it.id,
            name = it.name,
            closeApproachData = it.closeApproachData.fromJson(),
            absoluteMagnitude = it.absoluteMagnitude,
            diameter = it.diameter.DiameterFromJson(),
            isPotentiallyHazardous = it.isPotentiallyHazardous)
    }
}
fun List<AsteroidProperty>.toDao(): List<DatabaseAsteroids> {
    return map {
        DatabaseAsteroids(
            id= it.id,
            name = it.name,
            closeApproachData = it.closeApproachData.toJson(),
            absoluteMagnitude = it.absoluteMagnitude,
            diameter = it.diameter.toJson(),
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }
}

@TypeConverter
fun List<ApproachDate>.toJson(): String {
    val gson = Gson()
    val type = object : TypeToken<List<ApproachDate>>() {}.type
    return gson.toJson(this, type)
}

@TypeConverter
fun String.fromJson(): List<ApproachDate> {
    val gson = Gson()
    val type = object : TypeToken<List<ApproachDate>>() {}.type
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