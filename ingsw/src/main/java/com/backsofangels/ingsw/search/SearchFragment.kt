package com.backsofangels.ingsw.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.SearchView
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
    private var restaurantSearch = true
    private var hotelSearch = true
    private var attractionSearch = true

    private val structureApiService: StructureApi by lazy {
        RetrofitConfig.create(StructureApi::class)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSearchPromptBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchFragmentSearchView.isIconified = true

        toggleRestaurantsButton.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                restaurantSearch = restaurantSearch.not()
                Log.d(LogTags.UI_SEARCH_FRAGMENT.tag, "pressed toggleRestaurantsButton with value ${restaurantSearch.toString()}")
            }
        })

        toggleHotelsButton.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                hotelSearch = hotelSearch.not()
                Log.d(LogTags.UI_SEARCH_FRAGMENT.tag, "pressed toggleHotelsButton with value ${hotelSearch.toString()}")
            }
        })

        toggleAttractionsButton.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                attractionSearch = attractionSearch.not()
                if (attractionSearch) {
                    
                } else toggleAttractionsButton.setBackgroundColor(0xffb85f5f.toInt())

                Log.d(LogTags.UI_SEARCH_FRAGMENT.tag, "pressed toggleAttractionsButton with value ${attractionSearch.toString()}")
            }
        })

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
                        { _ -> Log.d(LogTags.RETROFIT_STRUCTURE_API.tag, "Restaurants query") },
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
                            { _ -> Log.d(LogTags.RETROFIT_STRUCTURE_API.tag, "Hotels query") },
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
                            { _ -> Log.d(LogTags.RETROFIT_STRUCTURE_API.tag, "Attractions") },
                            { error -> Log.e(LogTags.RETROFIT_STRUCTURE_API.tag, error.message.toString())}
                    )
        } else Log.d(LogTags.RETROFIT_STRUCTURE_API.tag, "No attractions query")
    }
}
