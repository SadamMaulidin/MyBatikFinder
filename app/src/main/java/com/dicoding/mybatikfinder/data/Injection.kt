package com.dicoding.mybatikfinder.data

import android.content.Context
import com.dicoding.mybatikfinder.data.pref.UserPreference
import com.dicoding.mybatikfinder.data.pref.UserRepository
import com.dicoding.mybatikfinder.data.pref.dataStore

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(pref)
    }
}