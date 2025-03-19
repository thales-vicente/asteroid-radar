package com.example.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.asteroidradar.database.AsteroidDao
import com.example.asteroidradar.database.DatabaseAsteroids
import com.example.asteroidradar.database.asDomainModel
import com.example.asteroidradar.database.toDao
import com.example.asteroidradar.network.AsteroidApi
import com.example.asteroidradar.network.AsteroidProperty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class AsteroidsRepository @Inject constructor(private val asteroidDao: AsteroidDao) {
    val asteroids: LiveData<List<AsteroidProperty>> =
        asteroidDao.getAsteroids().map {
            it.asDomainModel()
        }

    suspend fun getAsteroids(): Result<List<AsteroidProperty>> = safeApiCall {
        val response = AsteroidApi.retrofitService.getAsteroids()
        val asteroidsList = mutableListOf<AsteroidProperty>().apply {
            addAll(response.earth.item)
            addAll(response.earth.item2)
            addAll(response.earth.item3)
            addAll(response.earth.item4)
            addAll(response.earth.item5)
            addAll(response.earth.item6)
            addAll(response.earth.item7)
            addAll(response.earth.item8)
        }

        val databaseAsteroids: List<DatabaseAsteroids> = asteroidsList.toDao()
        asteroidDao.insertAll(*databaseAsteroids.toTypedArray())
        asteroidsList
    }
}

suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
    return withContext(Dispatchers.IO) {
        try {
            Result.success(apiCall())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> Result.failure(Exception("Network error"))
                else -> Result.failure(Exception("Unknown error"))
            }
        }
    }
}