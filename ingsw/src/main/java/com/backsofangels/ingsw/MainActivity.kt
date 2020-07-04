package com.backsofangels.ingsw

import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
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
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottomNavigationViewHome -> {
                    Log.d(LogTags.UI_NAVIGATION.tag, "home")
                }
                R.id.bottomNavigationViewFavorites -> {
                    Log.d("ui-navigation-log", "favorites")
                }
                R.id.bottomNavigationViewProfile -> {
                    Log.d("ui-navigation-log", "profile")
                }
            }
            true;
        }
    }
}
