package com.backsofangels.ingsw.signup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.backsofangels.ingsw.R
import com.backsofangels.ingsw.model.User
import com.backsofangels.ingsw.model.UserDto
import com.backsofangels.ingsw.retrofit.AuthApi
import com.backsofangels.ingsw.retrofit.RetrofitConfig
import com.backsofangels.ingsw.utils.LogTags
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_signup_layout.*

class SignUpFragment: Fragment(R.layout.fragment_signup_layout) {
    private var disposable: Disposable? = null

    private val authApi: AuthApi by lazy {
        RetrofitConfig.create(AuthApi::class)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_signup_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signUpFragmentSubmitButton.setOnClickListener {
            doSignUp()
        }
    }

    private fun doSignUp() {
        if (fieldsNullityCheck()) {
            return
        }
        val user = UserDto(
                signUpFragmentEmailEditText.text.toString(),
                signUpFragmentUsernameEditText.text.toString(),
                signUpFragmentFirstNameEditText.text.toString(),
                signUpFragmentLastNameEditText.text.toString(),
                signUpFragmentPasswordEditText.text.toString()
        )

        disposable = authApi.doSignUp(user)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { _ -> Log.d(LogTags.RETROFIT_AUTH_API.tag, "Called sign up")},
                        { error -> Log.e(LogTags.RETROFIT_AUTH_API.tag, error.message!!)}
                )
    }

    //returns true if one of the fields is blank or null, else returns false
    private fun fieldsNullityCheck(): Boolean {
        return signUpFragmentEmailEditText.text.isNullOrBlank()
                .or(signUpFragmentFirstNameEditText.text.isNullOrBlank())
                .or(signUpFragmentLastNameEditText.text.isNullOrBlank())
                .or(signUpFragmentUsernameEditText.text.isNullOrBlank())
                .or(signUpFragmentPasswordEditText.text.isNullOrBlank())
    }
}