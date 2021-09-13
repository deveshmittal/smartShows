package com.devesh.smartshows.dto.moviesDTO

data class MovieDto(
    val id: String,
    val movieFloorDto: List<MovieFloorDto> = arrayListOf()
)