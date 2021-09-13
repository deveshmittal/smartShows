package com.devesh.smartshows.ui.presentation.movieShowsBookingScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.devesh.smartshows.repository.MoviesDataRepository
import com.devesh.smartshows.repository.listenerAndDatabaseModel.ListenerAndDatabaseReference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookingScreenViewModel @Inject constructor(val repository: MoviesDataRepository) :
    ViewModel() {


    val listOfData = repository.moviesDto

    val currentSelectede = mutableStateOf("")

    val currentSelectedPillar = mutableStateOf("")

    val currentFloor = mutableStateOf(0)


    fun getMovieData(buildingId: String): ListenerAndDatabaseReference {
        return repository.getAllMovieDataOfBuilding(buildingId)
    }

}