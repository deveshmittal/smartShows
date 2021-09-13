package com.devesh.smartshows.repository

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.devesh.smartshows.dto.moviesDTO.MovieBoxDto
import com.devesh.smartshows.dto.moviesDTO.MovieDto
import com.devesh.smartshows.dto.moviesDTO.MovieFloorDto
import com.devesh.smartshows.dto.moviesDTO.MoviePillarDto
import com.devesh.smartshows.repository.listenerAndDatabaseModel.ListenerAndDatabaseReference


class MoviesDataRepository {

    private val database = Firebase.database
    private val movieDatabaseRef = database.getReference("movies")

    val moviesDto = mutableStateOf<MovieDto?>(null)

    fun getAllMovieDataOfBuilding(buildingId: String): ListenerAndDatabaseReference {
        val movieDatabaseBuildingRef = movieDatabaseRef.child(buildingId)

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                moviesDto.value = snapshot.key?.let { createMoviesDto(snapshot, it) }


                try {
                    Log.d("repo", moviesDto.value.toString())
                } catch (e: Exception) {

                }

            }

            override fun onCancelled(error: DatabaseError) {}
        }

        movieDatabaseBuildingRef.addValueEventListener(listener)

        return ListenerAndDatabaseReference(
            movieDatabaseBuildingRef,
            listener
        )
    }

    private fun createMoviesDto(dataSnapshot: DataSnapshot, id: String): MovieDto {

        val arrayListOfFloors = arrayListOf<MovieFloorDto>()

        //creating a list of floors
        for (floorDatabaseSnapshot in dataSnapshot.children) {
            val listOfPillarDto = arrayListOf<MoviePillarDto>()

            //creating list of pillars
            for (pillarDatabaseSnapShot in floorDatabaseSnapshot.children) {
                //creating a list of movies boxes
                val listOfMovieBoxDto = arrayListOf<MovieBoxDto>()
                for (movieBoxDatabaseSnapShot in pillarDatabaseSnapShot.children) {
                    val movieBox = movieBoxDatabaseSnapShot.getValue(MovieBoxDto::class.java)
                    if (movieBox != null) {
                        movieBox.id = movieBoxDatabaseSnapShot.key
                    }
                    if (movieBox != null) {
                        listOfMovieBoxDto.add(movieBox)
                    }
                }

                pillarDatabaseSnapShot.key?.let {
                    MoviePillarDto(
                        id = it,
                        listOfMovieBoxes = listOfMovieBoxDto
                    )
                }?.let {
                    listOfPillarDto.add(
                        it
                    )
                }
            }
            arrayListOfFloors.add(
                MovieFloorDto(
                    id = floorDatabaseSnapshot.key ?: "nan",
                    moviePillarDto = listOfPillarDto
                )
            )
        }
        return MovieDto(
            id = id,
            movieFloorDto = arrayListOfFloors
        )
    }

}