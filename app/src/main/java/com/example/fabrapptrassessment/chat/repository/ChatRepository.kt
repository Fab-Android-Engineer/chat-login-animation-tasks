package com.example.fabrapptrassessment.chat.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fabrapptrassessment.chat.model.AllData
import com.example.fabrapptrassessment.chat.model.ChatLogMessageModel
import com.example.fabrapptrassessment.chat.network.ApiService
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class ChatRepository @Inject constructor( private val chatApiService: ApiService) {
    // The internal MutableLiveData that stores the status of the most recent request
    private var _chat = MutableLiveData<List<ChatLogMessageModel>>()
    // The external immutable LiveData for the request data
    val chat : LiveData<List<ChatLogMessageModel>> = _chat
    /**
     * This fun access the Call
     * that sends a request to a webserver and returns a response.
     */
    fun getListChat ()  {
        return try {
             chatApiService.getChat().enqueue(object : Callback<AllData> {
                override fun onResponse(
                    call: Call<AllData>,
                    response: Response<AllData>
                ) {
                    _chat.postValue(response.body()?.data)
                }

                override fun onFailure(call: Call<AllData>, t: Throwable) {
                    Log.e("stack error", t.message!!)
                    t.printStackTrace()
                }
            })
        } catch (e : java.lang.Exception) {
            e.printStackTrace()
        }
    }
}
