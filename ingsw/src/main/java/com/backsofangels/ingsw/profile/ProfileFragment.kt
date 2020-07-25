package com.backsofangels.ingsw.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.backsofangels.ingsw.ApplicationFragmentFactory
import com.backsofangels.ingsw.R
import com.backsofangels.ingsw.databinding.FragmentProfileLayoutBinding
import com.backsofangels.ingsw.login.LoginFragment
import com.backsofangels.ingsw.model.User
import com.backsofangels.ingsw.retrofit.AuthApi
import com.backsofangels.ingsw.retrofit.RetrofitConfig
import com.backsofangels.ingsw.retrofit.ReviewApi
import com.backsofangels.ingsw.signup.SignUpFragment
import com.backsofangels.ingsw.utils.LogTags
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile_layout.*

class ProfileFragment: Fragment(R.layout.fragment_profile_layout) {
    private var _binding: FragmentProfileLayoutBinding? = null
    private val binding get() = _binding!!
    private var disposable: Disposable? = null
    private var recyclerAdapter: ProfileFragmentReviewRecyclerViewAdapter? = null

    //TODO: Implement server-side a /me api that returns user infos
    private val authApiService: AuthApi by lazy {
        RetrofitConfig.create(AuthApi::class)
    }

    private val reviewApiService: ReviewApi by lazy {
        RetrofitConfig.create(ReviewApi::class)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(LogTags.UI_FRAGMENT_NAVIGATION.tag, "Inflating ProfileFragment")
        return inflater.inflate(R.layout.fragment_profile_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerAdapter = ProfileFragmentReviewRecyclerViewAdapter()
        profileFragmentUserReviewsRecyclerView.apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(activity?.applicationContext)
        }
        profileFragmentDoLoginButton.setOnClickListener {
            this.activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(this.activity?.mainActivityFragmentPlaceholderView?.id!!, ApplicationFragmentFactory.instantiate(this.activity?.classLoader!!, LoginFragment::class.simpleName!!), "LoginFragment")
                    ?.addToBackStack("LoginFragment")
                    ?.commit()
        }
        profileFragmentDoSignUpButton.setOnClickListener {
            this.activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(this.activity?.mainActivityFragmentPlaceholderView?.id!!, ApplicationFragmentFactory.instantiate(this.activity?.classLoader!!, SignUpFragment::class.simpleName!!), "SignUpFragment")
                    ?.addToBackStack("SignUpFragment")
                    ?.commit()
        }
        if (checkProfileInfoSaved()) {
            profileFragmentUserReviewsRecyclerView.visibility = View.GONE
            profileFragmentDoLoginButton.visibility = View.VISIBLE
            profileFragmentDoSignUpButton.visibility = View.VISIBLE
        } else {
            profileFragmentUserReviewsRecyclerView.visibility = View.VISIBLE
            profileFragmentDoLoginButton.visibility = View.GONE
            profileFragmentDoSignUpButton.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable = null
    }

    //Returns true if the SharedPrefs are filled, false otherwise
    private fun checkProfileInfoSaved(): Boolean {
        val sharedPref = activity?.getSharedPreferences(getString(R.string.preferences_file_key), Context.MODE_PRIVATE)
        return sharedPref?.all?.isEmpty()!!
    }

    private fun retrieveUserInfo(): User {
        return User()
    }

    private fun retrieveUserReviews() {
        disposable = reviewApiService.getReviewsForUser(retrieveUserInfo(), null, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    { _ -> Log.d(LogTags.RETROFIT_STRUCTURE_API.tag, "got reviews") },
                    { error -> Log.e(LogTags.RETROFIT_STRUCTURE_API.tag, error.message.toString()) }
                )
    }
}