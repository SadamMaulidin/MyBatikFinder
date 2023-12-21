package com.dicoding.mybatikfinder.view.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.mybatikfinder.data.SignUpResponse
import com.dicoding.mybatikfinder.data.api.ApiConfig
import com.dicoding.mybatikfinder.data.pref.UserPreference
import com.dicoding.mybatikfinder.view.login.LoginViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel(private val pref: UserPreference): ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    val error = MutableLiveData("")
    val message = MutableLiveData("")
    private val TAG = LoginViewModel::class.simpleName

    fun signup(name: String, email: String, password: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().signup(name, email, password)
        client.enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                when (response.code()) {
                    400 -> error.postValue("400")
                    201 -> message.postValue("201")
                    else -> error.postValue("ERROR ${response.code()} : ${response.errorBody()}")
                }
                _isLoading.value = false
            }
            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                _isLoading.value = true
                Log.e(TAG, "onFailure Call: ${t.message}")
                error.postValue(t.message)
            }
        })
    }
}