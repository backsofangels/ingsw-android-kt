package com.backsofangels.ingsw.home.picksrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.backsofangels.ingsw.R
import com.backsofangels.ingsw.model.Structure
import kotlinx.android.synthetic.main.structure_cardview_layout.view.*

class StructuresRecyclerAdapter(): RecyclerView.Adapter<StructuresRecyclerAdapter.StructuresViewHolder>() {
    private var structuresDataSet: Array<Structure> = emptyArray()

        class StructuresViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(R.layout.structure_cardview_layout, parent, false)) {
        private var cardViewLayout: CardView? = null
        private var cardViewConstraintLayout: ConstraintLayout? = null
        private var cardViewImageView: ImageView? = null
        private var cardViewStructureNameTextView: TextView? = null
        private var cardViewStructureRatingBar: RatingBar? = null

        init {
            cardViewLayout = itemView.findViewById(R.id.cardViewLayout)
            cardViewConstraintLayout = itemView.findViewById(R.id.cardViewConstraintLayoutInternal)
            cardViewImageView = itemView.findViewById(R.id.structureCardViewPicture)
            cardViewStructureNameTextView = itemView.findViewById(R.id.structureCardViewNameTextView)
            cardViewStructureRatingBar = itemView.findViewById(R.id.cardViewStructureRatingBar)
        }

            fun bind(structure: Structure?) {
                cardViewStructureNameTextView?.text = structure?.name
                cardViewStructureRatingBar?.rating = structure?.averageScore?.toFloat()!!
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StructuresViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StructuresViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: StructuresViewHolder, position: Int) {
        if (structuresDataSet.isEmpty()) {
            return
        } else holder.bind(structuresDataSet[position])
    }

    override fun getItemCount(): Int {
        return 3
    }

    fun reloadDataSet(structures: Array<Structure>) {
        structuresDataSet = structures
        notifyDataSetChanged()
    }
}