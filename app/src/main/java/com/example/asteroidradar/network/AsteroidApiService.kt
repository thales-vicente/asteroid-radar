package com.example.asteroidradar.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://api.nasa.gov/neo/rest/v1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val json = Json { ignoreUnknownKeys = true }

val contentType = "application/json".toMediaType()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory(contentType = contentType))
    .baseUrl(BASE_URL)
    .build()

interface AsteroidApiService {
    @GET("feed?start_date=2025-02-25&end_date=2025-03-04&api_key=u7CSllCNi9k8icoAOnmPjHOtrYEiW9NOQ0dNBX3v")
    suspend fun getAsteroids(): Data

    @GET("https://api.nasa.gov/planetary/apod?api_key=u7CSllCNi9k8icoAOnmPjHOtrYEiW9NOQ0dNBX3v")
    suspend fun getImage(): ItemImage
}

object AsteroidApi {
    val retrofitService: AsteroidApiService by lazy {
        retrofit.create(AsteroidApiService::class.java)
    }


}
@Serializable
data class Data(
    @SerialName("near_earth_objects") val earth: Item,
)

@Serializable
data class Item(
    @SerialName("2025-02-25") val item: List<AsteroidProperty>,
    @SerialName("2025-02-26") val item2: List<AsteroidProperty>,
    @SerialName("2025-02-27") val item3: List<AsteroidProperty>,
    @SerialName("2025-02-28") val item4: List<AsteroidProperty>,
    @SerialName("2025-03-01") val item5: List<AsteroidProperty>,
    @SerialName("2025-03-02") val item6: List<AsteroidProperty>,
    @SerialName("2025-03-03") val item7: List<AsteroidProperty>,
    @SerialName("2025-03-04") val item8: List<AsteroidProperty>,
)

@Serializable
data class ItemImage(
    @SerialName("url") val item: String
)
