package com.devesh.smartshows.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devesh.smartshows.R
import com.devesh.smartshows.dto.moviesDTO.MallItem
import com.devesh.smartshows.views.interfaces.OnItemClickListener
import com.devesh.smartshows.views.viewHolders.MallItemViewHolder

class MallItemAdapter(private val list: List<MallItem>,private val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<MallItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MallItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.mall_item_layout, parent, false)
        return MallItemViewHolder(view,itemClickListener)
    }

    override fun onBindViewHolder(holder: MallItemViewHolder, position: Int) {
        val mallItem = list[position]
        holder.setData(mallItem)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}