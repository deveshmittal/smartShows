package com.devesh.smartshows.di

import com.devesh.smartshows.repository.MyBookingRepository
import com.devesh.smartshows.repository.MoviesDataRepository
import com.devesh.smartshows.repository.SlotBookingDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providesMovieDataRepository(): MoviesDataRepository {
        return MoviesDataRepository()
    }


    @Provides
    fun providesSlotDataRepository(): SlotBookingDataRepository {
        return SlotBookingDataRepository()
    }
  @Provides
    fun providesMyBookingRepository(): MyBookingRepository {
        return MyBookingRepository()
    }

}