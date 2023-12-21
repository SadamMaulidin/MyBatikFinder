package com.dicoding.mybatikfinder.view.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.mybatikfinder.data.SignInResponse
import com.dicoding.mybatikfinder.data.SignInResult
import com.dicoding.mybatikfinder.data.api.ApiConfig
import com.dicoding.mybatikfinder.data.pref.UserPreference
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel (private val pref: UserPreference) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    val error = MutableLiveData("")
    val message = MutableLiveData("")
    private val TAG = LoginViewModel::class.simpleName


    val signinResult = MutableLiveData<SignInResponse>()

    fun getUser(): LiveData<SignInResult> {
        return pref.getUser().asLiveData()
    }

    fun saveUser(userName: String, userId: String, userToken: String) {
        viewModelScope.launch {
            pref.saveUser(userName,userId,userToken)
        }
    }

    fun signout() {
        viewModelScope.launch {
            pref.signout()
        }
    }

    fun signin(email: String, password: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().signin(email, password)
        client.enqueue(object : Callback<SignInResponse> {
            override fun onResponse(call: Call<SignInResponse>, response: Response<SignInResponse>) {
                // parsing manual error code API
                when (response.code()) {
                    200 -> {
                        signinResult.postValue(response.body())
                        message.postValue("200")
                    }
                    400 -> error.postValue("400")
                    401 -> error.postValue("401")
                    else -> error.postValue("ERROR ${response.code()} : ${response.message()}")
                }

                _isLoading.value = false
            }

            override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                _isLoading.value = true
                Log.e(TAG, "onFailure Call: ${t.message}")
                error.postValue(t.message)
            }
        })
    }

}