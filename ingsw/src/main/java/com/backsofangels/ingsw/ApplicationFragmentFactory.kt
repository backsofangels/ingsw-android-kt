package com.backsofangels.ingsw

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.backsofangels.ingsw.favorites.FavoritesFragment
import com.backsofangels.ingsw.home.HomeFragment
import com.backsofangels.ingsw.login.LoginFragment
import com.backsofangels.ingsw.profile.ProfileFragment
import com.backsofangels.ingsw.search.SearchFragment
import com.backsofangels.ingsw.signup.SignUpFragment

object ApplicationFragmentFactory: FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className) {
            HomeFragment::class.simpleName -> HomeFragment()
            ProfileFragment::class.simpleName -> ProfileFragment()
            FavoritesFragment::class.simpleName -> FavoritesFragment()
            SearchFragment::class.simpleName -> SearchFragment()
            LoginFragment::class.simpleName -> LoginFragment()
            SignUpFragment::class.simpleName -> SignUpFragment()
            else -> super.instantiate(classLoader, className)
        }
    }
}