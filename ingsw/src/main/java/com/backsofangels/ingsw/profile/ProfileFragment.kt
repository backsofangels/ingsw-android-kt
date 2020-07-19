package com.backsofangels.ingsw.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.backsofangels.ingsw.R
import com.backsofangels.ingsw.retrofit.AuthApi
import com.backsofangels.ingsw.retrofit.RetrofitConfig
import com.backsofangels.ingsw.utils.LogTags
import io.reactivex.disposables.Disposable
import java.util.zip.Inflater

class ProfileFragment: Fragment(R.layout.fragment_profile_layout) {
    private var disposable: Disposable? = null

    //TODO: Implement server-side a /me api that returns user infos
    private val authApiService: AuthApi by lazy {
        RetrofitConfig.create(AuthApi::class)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun checkProfileInfosSaved(): Boolean {
        val sharedPref = activity?.getSharedPreferences(getString(R.string.preferences_file_key), Context.MODE_PRIVATE)
        return sharedPref?.all?.isEmpty()!!
    }

}