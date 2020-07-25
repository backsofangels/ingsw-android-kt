package com.backsofangels.ingsw.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.backsofangels.ingsw.R
import com.backsofangels.ingsw.model.Review

class ProfileFragmentReviewRecyclerViewHolder(inflater: LayoutInflater, parent: ViewGroup, context: Context): RecyclerView.ViewHolder(inflater.inflate(R.layout.fragment_profile_review, parent, false)) {
    private var structureName: TextView? = null
    private var reviewRating: RatingBar? = null
    private var reviewDescription: TextView? = null

    init {
        structureName = itemView.findViewById(R.id.profileFragmentReviewCardStructureName)
        reviewRating = itemView.findViewById(R.id.profileFragmentReviewCardStructureReviewRating)
        reviewDescription = itemView.findViewById(R.id.profileFragmentReviewCardStructureReviewText)
    }

    fun bind(review: Review) {
        structureName?.text = review.structure.name
        reviewRating?.rating = review.rating.toFloat()
        reviewDescription?.text = review.description
    }
}