package com.backsofangels.ingsw.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.backsofangels.ingsw.model.Review

class ProfileFragmentReviewRecyclerViewAdapter(): RecyclerView.Adapter<ProfileFragmentReviewRecyclerViewHolder>() {
    private var reviewsQueryResult: Array<Review> = emptyArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileFragmentReviewRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProfileFragmentReviewRecyclerViewHolder(inflater, parent, parent.context)
    }

    override fun onBindViewHolder(holder: ProfileFragmentReviewRecyclerViewHolder, position: Int) {
        if (reviewsQueryResult.isEmpty()) {
            return
        } else holder.bind(reviewsQueryResult[position])
    }

    override fun getItemCount(): Int {
        return reviewsQueryResult.size
    }

    fun reloadDataSet(dataSet: Array<Review>) {
        reviewsQueryResult = dataSet
        notifyDataSetChanged()
    }
}