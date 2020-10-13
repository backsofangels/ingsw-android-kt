package com.backsofangels.ingsw.structuredetails

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.backsofangels.ingsw.model.Review
import com.backsofangels.ingsw.model.Structure
import com.backsofangels.ingsw.model.User
import java.lang.RuntimeException

class ReviewsRecyclerViewAdapter(private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var reviewQueryResult: ArrayList<Review> = ArrayList()
    private val headerType = 0
    private val dataType = 1


    override fun getItemCount(): Int {
        Log.e("Reviews recyclerview", "Size = ${reviewQueryResult.size}")
        return reviewQueryResult.size + 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        if (viewType == headerType) {
            Log.e("Reviews recyclerview", "Header")
            return ReviewsPromptRecyclerViewViewHolder(inflater, parent, context)
        } else if (viewType == dataType) {
            Log.e("Reviews recyclerview", "Review")
            return ReviewsRecyclerViewViewHolder(inflater, parent, context)
        }
        Log.e("Reviews recyclerview", "no item")
        throw RuntimeException("No item matching type")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        try {
            if (holder is ReviewsRecyclerViewViewHolder) {
                val data = reviewQueryResult[position-1]
                Log.e("Reviews recyclerview", "binding review")
                holder.bind(data)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            headerType
        } else dataType
    }

    fun reloadDataSet(reviews: ArrayList<Review>) {
        if (reviews.isNullOrEmpty()) {
            return
        }
        reviewQueryResult = reviews
        notifyDataSetChanged()
    }
}