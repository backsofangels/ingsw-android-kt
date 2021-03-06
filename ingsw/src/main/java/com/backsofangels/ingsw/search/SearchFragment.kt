package com.backsofangels.ingsw.search

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.backsofangels.ingsw.R
import com.backsofangels.ingsw.databinding.FragmentSearchPromptBinding
import com.backsofangels.ingsw.retrofit.RetrofitConfig
import com.backsofangels.ingsw.retrofit.StructureApi
import com.backsofangels.ingsw.utils.LogTags
import com.backsofangels.ingsw.utils.hideKeyboard
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_search_prompt.*

class SearchFragment: Fragment(R.layout.fragment_search_prompt) {
    private var _binding: FragmentSearchPromptBinding? = null
    private val binding get() = _binding!!
    private lateinit var searchResultsRecyclerView: RecyclerView
    private lateinit var searchResultsRecyclerViewAdapter: SearchResultsRecyclerViewAdapter
    private var disposable: Disposable? = null

    //TODO: save state of toggles in user preferences to keep consistency between different searches and align UI
    private var restaurantSearch = false
    private var hotelSearch = false
    private var attractionSearch = false

    private val structureApiService: StructureApi by lazy {
        RetrofitConfig.create(StructureApi::class)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSearchPromptBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchFragmentSearchView.isIconified = true

        toggleRestaurantsButton.setOnClickListener {
            restaurantSearch = restaurantSearch.not()
            if (restaurantSearch) {
                toggleRestaurantsButton.setBackgroundColor(ContextCompat.getColor(activity?.applicationContext!!, R.color.toggleButtonPressed))
            } else toggleRestaurantsButton.setBackgroundColor(ContextCompat.getColor(activity?.applicationContext!!, R.color.toggleButtonDefault))
            Log.d(LogTags.UI_SEARCH_FRAGMENT.tag, "pressed toggleRestaurantsButton with value ${restaurantSearch.toString()}")
        }

        toggleHotelsButton.setOnClickListener {
            hotelSearch = hotelSearch.not()
            if (hotelSearch) {
                toggleHotelsButton.setBackgroundColor(ContextCompat.getColor(activity?.applicationContext!!, R.color.toggleButtonPressed))
            } else toggleHotelsButton.setBackgroundColor(ContextCompat.getColor(activity?.applicationContext!!, R.color.toggleButtonDefault))
            Log.d(LogTags.UI_SEARCH_FRAGMENT.tag, "pressed toggleHotelsButton with value ${hotelSearch.toString()}")
        }

        toggleAttractionsButton.setOnClickListener {
            attractionSearch = attractionSearch.not()
            if (attractionSearch) {
                toggleAttractionsButton.setBackgroundColor(ContextCompat.getColor(activity?.applicationContext!!, R.color.toggleButtonPressed))
            } else toggleAttractionsButton.setBackgroundColor(ContextCompat.getColor(activity?.applicationContext!!, R.color.toggleButtonDefault))

            Log.d(LogTags.UI_SEARCH_FRAGMENT.tag, "pressed toggleAttractionsButton with value ${attractionSearch.toString()}")
        }

        searchFragmentSearchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                hideKeyboard()
                performRestaurantSearch(restaurantSearch, searchFragmentSearchView.query.toString(), "Italy", null, null, 10, 0)
                performHotelSearch(hotelSearch, searchFragmentSearchView.query.toString(), "Italy", null, null, 10, 0)
                performAttractionSearch(attractionSearch, searchFragmentSearchView.query.toString(), "Italy", null, null, 10, 0)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
        searchResultsRecyclerView = RecyclerView(activity?.baseContext!!)
        searchResultsRecyclerViewAdapter = SearchResultsRecyclerViewAdapter()
        searchResultsRecyclerView.apply {
            adapter = searchResultsRecyclerViewAdapter
            layoutManager = LinearLayoutManager(activity?.baseContext!!, RecyclerView.VERTICAL, false)
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

    private fun performRestaurantSearch(searchToggle: Boolean, name: String?, nation: String?, town: String?, priceRange: Int?, limit: Int?, page: Int?) {
        if (searchToggle) {
            disposable = structureApiService.getRestaurants(name, nation, town, priceRange, limit, page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe (
                            { result -> result?.forEach { singleResult -> Log.d(LogTags.RETROFIT_STRUCTURE_API.tag, singleResult.toString()) } },
                            { error -> Log.e(LogTags.RETROFIT_STRUCTURE_API.tag, error.message.toString())}
                    )
        } else Log.d(LogTags.RETROFIT_STRUCTURE_API.tag, "No restaurant query")
    }

    private fun performHotelSearch(searchToggle: Boolean, name: String?, nation: String?, town: String?, priceRange: Int?, limit: Int?, page: Int?) {
        if (searchToggle) {
            disposable = structureApiService.getHotels(name, nation, town, priceRange, limit, page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe (
                            { result -> result?.forEach { singleResult -> Log.d(LogTags.RETROFIT_STRUCTURE_API.tag, singleResult.toString()) } },
                            { error -> Log.e(LogTags.RETROFIT_STRUCTURE_API.tag, error.message.toString())}
                    )
        } else Log.d(LogTags.RETROFIT_STRUCTURE_API.tag, "No hotels query")
    }

    private fun performAttractionSearch(searchToggle: Boolean, name: String?, nation: String?, town: String?, priceRange: Int?, limit: Int?, page: Int?) {
        if (searchToggle) {
            disposable = structureApiService.getAttractions(name, nation, town, priceRange, limit, page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe (
                            { result -> result?.forEach { singleResult -> Log.d(LogTags.RETROFIT_STRUCTURE_API.tag, singleResult.toString()) } },
                            { error -> Log.e(LogTags.RETROFIT_STRUCTURE_API.tag, error.message.toString())}
                    )
        } else Log.d(LogTags.RETROFIT_STRUCTURE_API.tag, "No attractions query")
    }
}
