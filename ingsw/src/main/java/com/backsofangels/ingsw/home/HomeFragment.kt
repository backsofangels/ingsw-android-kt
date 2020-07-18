package com.backsofangels.ingsw.home

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.backsofangels.ingsw.ApplicationFragmentFactory
import com.backsofangels.ingsw.R
import com.backsofangels.ingsw.databinding.FragmentHomeLayoutBinding
import com.backsofangels.ingsw.retrofit.RetrofitConfig
import com.backsofangels.ingsw.retrofit.StructureApi
import com.backsofangels.ingsw.utils.LogTags
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home_layout.*
import kotlinx.android.synthetic.main.fragment_search_prompt.*

class HomeFragment: Fragment(R.layout.fragment_home_layout) {
    private var _binding: FragmentHomeLayoutBinding? = null
    private val binding get() = _binding!!
    private lateinit var restaurantsRecyclerView: RecyclerView
    private var structuresRecyclerViewAdapter: StructuresRecyclerAdapter? = null
    private lateinit var hotelsRecyclerView: RecyclerView
    private lateinit var attractionsRecyclerView: RecyclerView
    private var disposable: Disposable? = null

    private val searchStructureApiService: StructureApi by lazy {
        RetrofitConfig.create(StructureApi::class)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
        restaurantsRecyclerView = RecyclerView(activity?.baseContext!!)
        hotelsRecyclerView = RecyclerView(activity?.baseContext!!)
        attractionsRecyclerView = RecyclerView(activity?.baseContext!!)
        homeFragmentTopPicksLinearLayout.addView(restaurantsRecyclerView)
        homeFragmentTopPicksLinearLayout.addView(hotelsRecyclerView)
        homeFragmentTopPicksLinearLayout.addView(attractionsRecyclerView)
        structuresRecyclerViewAdapter = StructuresRecyclerAdapter()
        restaurantsRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity?.baseContext!!, RecyclerView.HORIZONTAL, false)
            adapter = structuresRecyclerViewAdapter
        }
        hotelsRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity?.baseContext!!, RecyclerView.HORIZONTAL, false)
            adapter = structuresRecyclerViewAdapter
        }
        attractionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity?.baseContext!!, RecyclerView.HORIZONTAL, false)
            adapter = structuresRecyclerViewAdapter
        }
        homeFragmentSearchView.setOnSearchClickListener {
            Log.d("transition", "transition")
            val fragment = ApplicationFragmentFactory.instantiate(this.activity?.classLoader!!, "SearchFragment")
            this.activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.mainActivityFragmentPlaceholderView, fragment, "SearchFragment")
                    ?.addToBackStack("SearchFragment")
                    ?.commit()
        }
    }

    override fun onPause() {
        super.onPause()
        disposable = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun performStructureSearch(limit: Int, page: Int) {
        disposable = searchStructureApiService.getAllStructures(limit, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {result ->
                            run {
                                Log.d(LogTags.RETROFIT_STRUCTURE_API.tag, result.size.toString())
                                structuresRecyclerViewAdapter?.reloadDataSet(result)
                            }
                        },
                        {error -> Log.e(LogTags.RETROFIT_STRUCTURE_API.tag, error.message!!)}
                )
    }

}