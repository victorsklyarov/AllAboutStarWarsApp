package com.zeroillusion.allaboutstarwarsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zeroillusion.allaboutstarwarsapp.data.local.entity.FilmEntity
import com.zeroillusion.allaboutstarwarsapp.data.local.entity.PersonEntity
import com.zeroillusion.allaboutstarwarsapp.data.local.entity.PlanetEntity
import com.zeroillusion.allaboutstarwarsapp.data.local.entity.StarshipEntity

@Database(
    entities = [PersonEntity::class, StarshipEntity::class, PlanetEntity::class, FilmEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class StarWarsDatabase : RoomDatabase() {

    abstract val dao: StarWarsDao
}