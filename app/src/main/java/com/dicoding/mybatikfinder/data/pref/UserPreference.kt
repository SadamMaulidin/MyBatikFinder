package com.dicoding.mybatikfinder.data.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.dicoding.mybatikfinder.data.SignInResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("session")

class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {

    fun getToken(): Flow<String> = dataStore.data.map { it[TOKEN_KEY] ?: "Tidak diatur" }

    suspend fun saveUser(userName: String, userId: String, userToken: String) {
        dataStore.edit { preferences ->
            preferences[NAME_KEY] = userName
            preferences[USERID_KEY] = userId
            preferences[TOKEN_KEY] = userToken
        }
    }

    fun getUser(): Flow<SignInResult> {
        return dataStore.data.map { preferences ->
            SignInResult(
                preferences[NAME_KEY] ?:"",
                preferences[USERID_KEY] ?:"",
                preferences[TOKEN_KEY] ?:"",
            )
        }
    }

    suspend fun signout() {
        dataStore.edit { preferences ->
            preferences[NAME_KEY] = ""
            preferences[USERID_KEY] = ""
            preferences[TOKEN_KEY] = ""
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        private val NAME_KEY = stringPreferencesKey("name")
        private val USERID_KEY = stringPreferencesKey("userId")
        private val TOKEN_KEY = stringPreferencesKey("token")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}