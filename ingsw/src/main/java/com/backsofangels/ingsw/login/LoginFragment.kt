package com.backsofangels.ingsw.login

import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.backsofangels.ingsw.R
import com.backsofangels.ingsw.retrofit.AuthApi
import com.backsofangels.ingsw.retrofit.RetrofitConfig
import com.backsofangels.ingsw.utils.LogTags
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_login_layout.*
import java.nio.charset.Charset

class LoginFragment: Fragment(R.layout.fragment_login_layout) {
    private val authApi: AuthApi by lazy {
        RetrofitConfig.create(AuthApi::class)
    }

    private var disposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginFragmentLoginButton.setOnClickListener {
            doLogin(loginFragmentUsernameEditText?.text.toString(), loginFragmentPasswordEditText.text.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable = null
    }

    private fun doLogin(username: String?, password: String?) {
        if (!username.isNullOrBlank() && !password.isNullOrBlank()) {
            //Kotlin è bellissimo
            val header = "Basic " + Base64.encodeToString("${username}:${password}".toByteArray(Charset.forName("utf-8")), Base64.NO_WRAP)

            disposable = authApi.doLogin(header)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { _ -> run {
                                Log.d(LogTags.RETROFIT_STRUCTURE_API.tag, "do login")
                                this.activity?.supportFragmentManager
                                        ?.beginTransaction()
                                        ?.remove(this)
                                        ?.commit()
                                this.activity?.supportFragmentManager?.popBackStack()
                            }},
                            { error -> run {
                                Log.e(LogTags.RETROFIT_STRUCTURE_API.tag, error.message.toString())
                            }}
                    )
        } else Log.d(LogTags.RETROFIT_STRUCTURE_API.tag, "No username or password")
    }
}