package com.example.fabrapptrassessment.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fabrapptrassessment.chat.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val apiService: ApiService) : ViewModel()  {
    var user = UserModel("", "")

    // The internal MutableLiveData that stores the status of the most recent request
    private var _loginRes = MutableLiveData<LoginResponse>()
    // The external immutable LiveData for the request data
    val loginRes : LiveData<LoginResponse> = _loginRes

    private val password : String
        get() = user.password

    private val email : String
        get() = user.email

    init {
        viewModelScope.launch {
            Log.i("userInit", " $email and $password")
            processInputUsers(email, password)
        }
    }

    private fun processInputUsers(email : String, password : String)  {
        try {
            Log.i("emailInside", "info $email and $password")
            return  apiService.sendUsersInfo(email, password).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    Log.i("res", "response ${response.body()}")
                    _loginRes.postValue(response.body())
                }
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e("failure", "Failed to send data")
                    Log.e("stack error", t.message!!)
                    t.printStackTrace()
                }
            })
        } catch (e : java.lang.Exception) {
            e.printStackTrace()
        }
    }
}


