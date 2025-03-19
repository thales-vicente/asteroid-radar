package com.example.asteroidradar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asteroidradar.network.AsteroidApi
import com.example.asteroidradar.network.AsteroidProperty
import com.example.asteroidradar.repository.AsteroidsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class AsteroidListViwModel @Inject constructor(private val asteroidsRepository: AsteroidsRepository) :
    ViewModel() {
    val asteroids: MutableLiveData<List<AsteroidProperty>> = MutableLiveData()
    val image: MutableLiveData<String> = MutableLiveData()
    fun getAsteroids() {
        viewModelScope.launch() {
            asteroidsRepository.getAsteroids()
                .onSuccess { asteroids.value = it }
                .onFailure { asteroids.value = ArrayList() }
            val imageResponse = AsteroidApi.retrofitService.getImage()
            image.value = imageResponse.item

        }
    }
}
