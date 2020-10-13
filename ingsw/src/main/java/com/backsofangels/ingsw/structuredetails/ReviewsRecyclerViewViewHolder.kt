package com.backsofangels.ingsw.structuredetails

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.backsofangels.ingsw.R
import com.backsofangels.ingsw.model.Review
import com.backsofangels.ingsw.model.User

class ReviewsRecyclerViewViewHolder(inflater: LayoutInflater, parent: ViewGroup, context: Context): RecyclerView.ViewHolder(inflater.inflate(R.layout.structure_detail_review_display, parent, false)) {
    private var profilePicture: ImageView? = null
    private var reviewRatingBar: RatingBar? = null
    private var reviewTitle: TextView? = null
    private var reviewDescription: TextView? = null

    init {
        profilePicture = itemView.findViewById(R.id.reviewCardUserProfileImage)
        reviewRatingBar = itemView.findViewById(R.id.reviewCardRatingScore)
        reviewTitle = itemView.findViewById(R.id.reviewCardReviewTitle)
        reviewDescription = itemView.findViewById(R.id.reviewCardReviewText)
    }

    fun bind(review: Review?) {
        if (review != null) {
            Log.d("ReviewViewHolder", "Review not null, $review")
            reviewTitle?.text = review.title
            reviewRatingBar?.numStars = review.rating
            reviewDescription?.text = review.description
        } else {
            reviewRatingBar?.numStars = 0
            reviewDescription?.text = "No description"
        }

    }
}