package com.example.asteroidradar.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Dao
interface AsteroidDao{
    @Query("select * from databaseasteroids")
    fun getAsteroids(): LiveData<List<DatabaseAsteroids>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg asteroids: DatabaseAsteroids)
}
@Database(entities = [DatabaseAsteroids::class], version = 1)
abstract class AsteroidDatabase: RoomDatabase() {
    abstract fun asteroidDao(): AsteroidDao

}