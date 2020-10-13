package com.backsofangels.ingsw.search

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.backsofangels.ingsw.R
import com.backsofangels.ingsw.model.Structure
import com.backsofangels.ingsw.structuredetails.StructureDetailsFragment
import com.backsofangels.ingsw.utils.LogTags
import kotlinx.android.synthetic.main.activity_main.*

class SearchResultsRecyclerViewAdapter(private val callingFragment: Fragment): RecyclerView.Adapter<SearchResultsRecyclerViewViewHolder>() {
    private var queryResultDataSet: ArrayList<Structure> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsRecyclerViewViewHolder {
        Log.d(LogTags.APP_DEBUG_GENERIC.tag, "Inflating viewholder")
        val inflater = LayoutInflater.from(parent.context)
        return SearchResultsRecyclerViewViewHolder(inflater, parent, parent.context)
    }

    override fun onBindViewHolder(holder: SearchResultsRecyclerViewViewHolder, position: Int) {
        Log.d(LogTags.APP_DEBUG_GENERIC.tag, "About to bind")
        if (queryResultDataSet.isEmpty()) {
            Log.d(LogTags.APP_DEBUG_GENERIC.tag, "Binding viewholder with empty dataset")
            return
        } else {
            Log.d(LogTags.APP_DEBUG_GENERIC.tag, "Binding viewholder with not empty dataset")
            holder.bind(queryResultDataSet[position])
            holder.itemView.setOnClickListener {
                callingFragment.fragmentManager
                        ?.beginTransaction()
                        ?.replace(R.id.mainActivityFragmentPlaceholderView, StructureDetailsFragment(queryResultDataSet[position]), "StructureDetailsFragment")
                        ?.addToBackStack("StructureDetailsFragment")
                        ?.commit()
            }
        }
    }

    override fun getItemCount(): Int {
        return queryResultDataSet.size
    }


    fun reloadDataSet(dataSet: ArrayList<Structure>) {
        queryResultDataSet.addAll(dataSet)
        Log.d(LogTags.APP_DEBUG_GENERIC.tag, "Refreshed dataset with size ${queryResultDataSet.size}")
        notifyDataSetChanged()
    }
}