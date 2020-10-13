package com.backsofangels.ingsw.structuredetails

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.backsofangels.ingsw.R
import com.backsofangels.ingsw.utils.LogTags

class ReviewsPromptRecyclerViewViewHolder(inflater: LayoutInflater, parent: ViewGroup, context: Context): RecyclerView.ViewHolder(inflater.inflate(R.layout.structure_detail_review_prompt_header, parent, false)) {
    private var promptButton: Button? = null

    init {
        promptButton = itemView.findViewById(R.id.reviewHeaderPromptButton)
    }

    fun bind() {
        promptButton?.setOnClickListener {
            Log.d(LogTags.APP_DEBUG_GENERIC.tag, "Pressed review prompt button")
        }
    }
}