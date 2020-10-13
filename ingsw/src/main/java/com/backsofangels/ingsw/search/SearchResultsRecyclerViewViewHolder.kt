package com.backsofangels.ingsw.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.backsofangels.ingsw.R
import com.backsofangels.ingsw.model.Hotel
import com.backsofangels.ingsw.model.Restaurant
import com.backsofangels.ingsw.model.Structure
import com.backsofangels.ingsw.model.TouristAttraction
import java.util.zip.Inflater

class SearchResultsRecyclerViewViewHolder(inflater: LayoutInflater, parent: ViewGroup, context: Context): RecyclerView.ViewHolder(inflater.inflate(R.layout.structure_cardview_layout, parent, false)) {
    private var cardViewLayout: CardView? = null
    private var cardViewConstraintLayout: ConstraintLayout? = null
    private var cardViewImageView: ImageView? = null
    private var cardViewStructureNameTextView: TextView? = null
    private var cardViewStructureRatingBar: RatingBar? = null
    private var cardViewStructureLabel: TextView? = null

    init {
        cardViewLayout = itemView.findViewById(R.id.cardViewLayout)
        cardViewConstraintLayout = itemView.findViewById(R.id.cardViewConstraintLayoutInternal)
        cardViewImageView = itemView.findViewById(R.id.structureCardViewPicture)
        cardViewStructureNameTextView = itemView.findViewById(R.id.structureCardViewNameTextView)
        cardViewStructureRatingBar = itemView.findViewById(R.id.cardViewStructureRatingBar)
        cardViewStructureLabel = TextView(context)
    }

    fun bind(structure: Structure?) {
        cardViewStructureNameTextView?.text = structure?.name
        cardViewStructureRatingBar?.rating = structure?.averageScore?.toFloat()!!
        when (structure) {
            is Restaurant -> cardViewStructureLabel?.text = "R"
            is Hotel -> cardViewStructureLabel?.text = "H"
            is TouristAttraction -> cardViewStructureLabel?.text = "A"
        }
    }

}