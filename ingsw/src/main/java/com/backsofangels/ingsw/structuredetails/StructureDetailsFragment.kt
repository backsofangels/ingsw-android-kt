package com.backsofangels.ingsw.structuredetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.backsofangels.ingsw.R

class StructureDetailsFragment: Fragment(R.layout.fragment_structure_details_layout) {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_structure_details_layout, container, false)
    }
}