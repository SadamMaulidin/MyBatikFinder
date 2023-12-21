package com.dicoding.mybatikfinder.view.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.mybatikfinder.view.fragment.AboutFragment
import com.dicoding.mybatikfinder.view.fragment.HistoryFragment
import com.dicoding.mybatikfinder.view.fragment.HomeFragment
import com.dicoding.mybatikfinder.view.fragment.ProfileFragment
import com.dicoding.mybatikfinder.R
import com.dicoding.mybatikfinder.data.pref.UserPreference
import com.dicoding.mybatikfinder.databinding.ActivityMainBinding
import com.dicoding.mybatikfinder.view.ViewModelFactory
import com.dicoding.mybatikfinder.view.addphoto.AddPhotoFragment
import com.dicoding.mybatikfinder.view.login.LoginActivity
import com.dicoding.mybatikfinder.view.login.LoginViewModel
import com.dicoding.mybatikfinder.view.signup.SignUpViewModel
import com.dicoding.mybatikfinder.view.signup.SignupActivity

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[MainViewModel::class.java]

        loginViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[LoginViewModel::class.java]

        loginViewModel.getUser().observe(this){user->
            if (user.userId.isEmpty()){
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

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