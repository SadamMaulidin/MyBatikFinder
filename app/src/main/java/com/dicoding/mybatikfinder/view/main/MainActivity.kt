package com.dicoding.mybatikfinder.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dicoding.mybatikfinder.AboutFragment
import com.dicoding.mybatikfinder.HistoryFragment
import com.dicoding.mybatikfinder.HomeFragment
import com.dicoding.mybatikfinder.ProfileFragment
import com.dicoding.mybatikfinder.R
import com.dicoding.mybatikfinder.databinding.ActivityMainBinding
import com.dicoding.mybatikfinder.view.addphoto.AddPhotoFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.home -> replaceFragment(HomeFragment())
                R.id.history -> replaceFragment(HistoryFragment())
                R.id.addphoto -> replaceFragment(AddPhotoFragment())
                R.id.profile -> replaceFragment(ProfileFragment())
                R.id.aboutapp -> replaceFragment(AboutFragment())

                else ->{

                }
            }
            true
        }
    }

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}