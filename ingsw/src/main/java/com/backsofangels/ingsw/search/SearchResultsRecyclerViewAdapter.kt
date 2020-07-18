package com.backsofangels.ingsw.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.backsofangels.ingsw.model.Structure

class SearchResultsRecyclerViewAdapter(): RecyclerView.Adapter<SearchResultsRecyclerViewViewHolder>() {
    private var queryResultDataSet: Array<Structure> = emptyArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsRecyclerViewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SearchResultsRecyclerViewViewHolder(inflater, parent, parent.context)
    }

    override fun onBindViewHolder(holder: SearchResultsRecyclerViewViewHolder, position: Int) {
        if (queryResultDataSet.isEmpty()) {
            return
        } else holder.bind(queryResultDataSet[position])
    }

    override fun getItemCount(): Int {
        return queryResultDataSet.size
    }

    fun reloadDataSet(dataSet: Array<Structure>) {
        queryResultDataSet = dataSet
        notifyDataSetChanged()
    }
}