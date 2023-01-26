package com.sriv.shivam.musicwiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.sriv.shivam.musicwiki.repository.MusicWikiRepository
import com.sriv.shivam.musicwiki.viewmodel.MusicWikiViewModel
import com.sriv.shivam.musicwiki.viewmodel.MusicWikiViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MusicWikiViewModel
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val musicWikiRepository = MusicWikiRepository()
        val viewModelProviderFactory = MusicWikiViewModelProviderFactory(musicWikiRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[MusicWikiViewModel::class.java]


        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.homeNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController
    }
}