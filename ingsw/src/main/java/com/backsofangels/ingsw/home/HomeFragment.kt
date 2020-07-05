package com.backsofangels.ingsw.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.backsofangels.ingsw.R
import com.backsofangels.ingsw.utils.LogTags
import kotlinx.android.synthetic.main.fragment_home_layout.view.*

class HomeFragment: Fragment() {
    @BindView(R.id.homeFragmentConstraintLayout)
    lateinit var constraintLayout: ConstraintLayout

    @BindView(R.id.homeFragmentSearchView)
    lateinit var homeFragmentSearchView: SearchView

    @BindView(R.id.homeFragmentTopPicksRecyclerView)
    lateinit var topPickRecyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(view)

    }
}