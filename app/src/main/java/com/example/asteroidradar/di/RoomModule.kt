package com.example.asteroidradar.di

import android.content.Context
import androidx.room.Room
import com.example.asteroidradar.database.AsteroidDao
import com.example.asteroidradar.database.AsteroidDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    fun providerAsteroidDatabase(@ApplicationContext context: Context): AsteroidDatabase{
        return Room.databaseBuilder(context, AsteroidDatabase::class.java,
            "asteroids").build()
    }
    @Provides
    fun providerAsteroidDao( asteroidDatabase: AsteroidDatabase): AsteroidDao{
        return asteroidDatabase.asteroidDao()
    }
}