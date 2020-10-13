package com.backsofangels.ingsw.structuredetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.backsofangels.ingsw.R
import com.backsofangels.ingsw.databinding.FragmentStructureDetailsLayoutBinding
import com.backsofangels.ingsw.model.*
import com.backsofangels.ingsw.retrofit.RetrofitConfig
import com.backsofangels.ingsw.retrofit.ReviewApi
import com.backsofangels.ingsw.utils.LogTags
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_structure_details_layout.*

class StructureDetailsFragment(var structure: Structure): Fragment(R.layout.fragment_structure_details_layout) {
    private val tagsArray: Array<Int> = arrayOf(
            R.id.structureFragmentTagTextViewOne,
            R.id.structureFragmentTagTextViewTwo,
            R.id.structureFragmentTagTextViewThree,
            R.id.structureFragmentTagTextViewFour,
            R.id.structureFragmentTagTextViewFive,
            R.id.structureFragmentTagTextViewSix
        )

    private var _binding: FragmentStructureDetailsLayoutBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerViewAdapter: ReviewsRecyclerViewAdapter
    private lateinit var reviewsRecyclerView: RecyclerView
    private var disposable: Disposable? = null

    private val reviewsApiService: ReviewApi by lazy {
        RetrofitConfig.create(ReviewApi::class)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentStructureDetailsLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        structureFragmentStructureNameTextView.text = structure.name
        recyclerViewAdapter = ReviewsRecyclerViewAdapter(this.context!!)
        when(structure) {
            is Restaurant -> {
                val rest = structure as Restaurant
                rest.cookingTypes.forEachIndexed {
                    index, cookingType -> run {
                    //Rubber banding, deve essere settato un numero massimo di tag da server
                    if (index >= tagsArray.size) {
                        return@run
                    }
                    //Fine rubber banding, il numero max è 6
                        this.view?.findViewById<TextView>(tagsArray[index])?.text = cookingType
                    }
                }
            }

            is Hotel -> {
                val hotel = structure as Hotel
                hotel.services.forEachIndexed {
                    index, service -> run {
                    //Rubber banding, deve essere settato un numero massimo di tag da server
                    if (index >= tagsArray.size) {
                        return@run
                    }
                    //Fine rubber banding, il numero max è 6
                        this.view?.findViewById<TextView>(tagsArray[index])?.text = service
                    }
                }
            }

            is TouristAttraction -> {
                val attraction = structure as TouristAttraction
                attraction.services.forEachIndexed {
                    index, service -> run {
                    //Rubber banding, deve essere settato un numero massimo di tag da server
                    if (index >= tagsArray.size) {
                        return@run
                    }
                    //Fine rubber banding, il numero max è 6
                        this.view?.findViewById<TextView>(tagsArray[index])?.text = service
                    }
                }
            }
        }

        structureFragmentAddressTextView.text = String.format(
                resources.getString(R.string.structureAddressTemplate),
                structure.road,
                structure.houseNumber,
                structure.town
        )



        structureFragmentReviewsRecyclerView.apply {
            Log.d(LogTags.APP_DEBUG_GENERIC.tag, "Applying adapter")
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            adapter = recyclerViewAdapter
            Log.d(LogTags.APP_DEBUG_GENERIC.tag, "Applying settings to recyclerview")
        }

        doReviewQuery(structure.id.toInt())
    }

    private fun doReviewQuery(structureId: Int) {
        var queryResult: ArrayList<Review> = ArrayList()

        disposable = reviewsApiService.getReviewsForStructure(structureId, null, null, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> result?.forEach {
                            singleResult -> queryResult.add(singleResult)
                            Log.d(LogTags.APP_DEBUG_GENERIC.tag, "Inserted value $singleResult")
                        }}, //onNext
                        { error -> Log.e(LogTags.APP_DEBUG_GENERIC.tag, "Error fetching reviews: $error")}, //onError
                        { recyclerViewAdapter.reloadDataSet(queryResult)}  //onComplete
                )
    }

}