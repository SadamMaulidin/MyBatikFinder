package com.dicoding.mybatikfinder.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.mybatikfinder.data.pref.UserPreference
import kotlinx.coroutines.launch

class MainViewModel(private val pref: UserPreference) : ViewModel() {
    fun getToken(): LiveData<String> {
        return pref.getToken().asLiveData()
    }


}