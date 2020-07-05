package com.backsofangels.ingsw

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import butterknife.BindView
import butterknife.ButterKnife
import com.backsofangels.ingsw.favorites.FavoritesFragment
import com.backsofangels.ingsw.home.HomeFragment
import com.backsofangels.ingsw.profile.ProfileFragment
import com.backsofangels.ingsw.utils.LogTags
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    @BindView(R.id.mainActivityBottomNavigationView)
    lateinit var bottomNavigationView: BottomNavigationView

    @BindView(R.id.mainActivityFragmentPlaceholderView)
    lateinit var fragmentPlaceHolder: FrameLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        val fragmentManager = this.supportFragmentManager

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottomNavigationViewHome -> {
                    Log.d(LogTags.UI_ACTIVITY_NAVIGATION.tag, "Inflating HomeFragment")
                    fragmentManager.
                            beginTransaction()
                            .add(fragmentPlaceHolder.id, ApplicationFragmentFactory.instantiate(this.classLoader, HomeFragment::class.simpleName!!), "HomeFragment")
                            .commit()
                }
                R.id.bottomNavigationViewFavorites -> {
                    Log.d(LogTags.UI_ACTIVITY_NAVIGATION.tag, "favorites")
                    fragmentManager.beginTransaction()
                            .add(fragmentPlaceHolder.id, ApplicationFragmentFactory.instantiate(this.classLoader, FavoritesFragment::class.simpleName!!), "FavoritesFragment")
                            .commit()
                }
                R.id.bottomNavigationViewProfile -> {
                    Log.d(LogTags.UI_ACTIVITY_NAVIGATION.tag, "profile")
                    fragmentManager
                            .beginTransaction()
                            .add(fragmentPlaceHolder.id, ApplicationFragmentFactory.instantiate(this.classLoader, ProfileFragment::class.simpleName!!), "ProfileFragment")
                            .commit()
                }
            }
            true;
        }
        bottomNavigationView.selectedItemId = R.id.bottomNavigationViewHome
    }
}
