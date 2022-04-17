package com.example.armut.ui

import android.os.Bundle
import com.example.armut.R
import com.example.armut.base.BaseActivity
import com.example.armut.core.extensions.replaceFragmentSafely
import com.example.armut.databinding.ActivityMainBinding
import com.example.armut.ui.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

/**
 * The MainActivity.kt, Main activity class, launcher activity
 * @author Malik Dawar, malikdawar@hotmail.com
 */

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        replaceFragmentSafely(HomeFragment())
    }

}