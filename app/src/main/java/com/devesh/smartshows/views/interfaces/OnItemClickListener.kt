package com.devesh.smartshows.views.interfaces

import com.devesh.smartshows.dto.moviesDTO.MallItem

interface OnItemClickListener {

    fun onItemClicked(mallItem: MallItem)

    fun onDirectionsClicked()

}