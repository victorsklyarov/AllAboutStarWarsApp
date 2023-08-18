package com.zeroillusion.allaboutstarwarsapp.di

import android.app.Application
import androidx.room.Room
import com.zeroillusion.allaboutstarwarsapp.data.local.StarWarsDatabase
import com.zeroillusion.allaboutstarwarsapp.data.remote.SWAPI
import com.zeroillusion.allaboutstarwarsapp.data.repository.StarWarsRepositoryImpl
import com.zeroillusion.allaboutstarwarsapp.domain.repository.StarWarsRepository
import com.zeroillusion.allaboutstarwarsapp.domain.use_cases.ChangeFavoriteStatus
import com.zeroillusion.allaboutstarwarsapp.domain.use_cases.GetAllFavorites
import com.zeroillusion.allaboutstarwarsapp.domain.use_cases.SearchByName
import com.zeroillusion.allaboutstarwarsapp.domain.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StarWarsModule {

    @Provides
    @Singleton
    fun provideChangeFavoriteStatus(repository: StarWarsRepository): ChangeFavoriteStatus {
        return ChangeFavoriteStatus(repository)
    }

    @Provides
    @Singleton
    fun provideGetAllFavorites(repository: StarWarsRepository): GetAllFavorites {
        return GetAllFavorites(repository)
    }

    @Provides
    @Singleton
    fun provideSearchByName(repository: StarWarsRepository): SearchByName {
        return SearchByName(repository)
    }

    @Provides
    @Singleton
    fun provideStarWarsRepository(
        db: StarWarsDatabase,
        api: SWAPI
    ): StarWarsRepository {
        return StarWarsRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideStarWarsDatabase(app: Application): StarWarsDatabase {
        return Room.databaseBuilder(
            app,
            StarWarsDatabase::class.java,
            "starwars_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideSWAPI(): SWAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SWAPI::class.java)
    }
}