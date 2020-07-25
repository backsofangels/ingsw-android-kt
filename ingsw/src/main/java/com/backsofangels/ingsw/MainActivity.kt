package com.backsofangels.ingsw

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.backsofangels.ingsw.databinding.ActivityMainBinding
import com.backsofangels.ingsw.favorites.FavoritesFragment
import com.backsofangels.ingsw.home.HomeFragment
import com.backsofangels.ingsw.model.Structure
import com.backsofangels.ingsw.profile.ProfileFragment
import com.backsofangels.ingsw.retrofit.AuthApi
import com.backsofangels.ingsw.retrofit.RetrofitConfig
import com.backsofangels.ingsw.retrofit.ReviewApi
import com.backsofangels.ingsw.retrofit.StructureApi
import com.backsofangels.ingsw.utils.LogTags
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var bindingModule: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Binding views to module
        bindingModule = ActivityMainBinding.inflate(layoutInflater)
        val view = bindingModule.root
        setContentView(view)

        //Setting fragment navigation
        val fragmentManager = this.supportFragmentManager
        bindingModule.mainActivityBottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottomNavigationViewHome -> {
                    Log.d(LogTags.UI_ACTIVITY_NAVIGATION.tag, "Inflating HomeFragment")
                    fragmentManager.
                    beginTransaction()
                            .replace(bindingModule.mainActivityFragmentPlaceholderView.id, ApplicationFragmentFactory.instantiate(this.classLoader, HomeFragment::class.simpleName!!), "HomeFragment")
                            .addToBackStack("HomeFragment")
                            .commit()
                }
                R.id.bottomNavigationViewFavorites -> {
                    Log.d(LogTags.UI_ACTIVITY_NAVIGATION.tag, "Inflating Favorites")
                    fragmentManager.beginTransaction()
                            .replace(bindingModule.mainActivityFragmentPlaceholderView.id, ApplicationFragmentFactory.instantiate(this.classLoader, FavoritesFragment::class.simpleName!!), "FavoritesFragment")
                            .addToBackStack("FavoritesFragment")
                            .commit()
                }
                R.id.bottomNavigationViewProfile -> {
                    Log.d(LogTags.UI_ACTIVITY_NAVIGATION.tag, "Inflating Profile")
                    fragmentManager
                            .beginTransaction()
                            .replace(bindingModule.mainActivityFragmentPlaceholderView.id, ApplicationFragmentFactory.instantiate(this.classLoader, ProfileFragment::class.simpleName!!), "ProfileFragment")
                            .addToBackStack("ProfileFragment")
                            .commit()
                }
            }
            true;
        }

        //Setting default view of bottomNavigation
        bindingModule.mainActivityBottomNavigationView.selectedItemId = R.id.bottomNavigationViewHome
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
    }
}
