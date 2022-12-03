package com.example.fabrapptrassessment.chat.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fabrapptrassessment.chat.model.ChatLogMessageModel
import com.example.fabrapptrassessment.chat.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChatViewModel @Inject constructor(private val chatRepository: ChatRepository) : ViewModel() {
    // The external LiveData LiveData for the request status
    val chat : LiveData<List<ChatLogMessageModel>> = chatRepository.chat

    init {
        getData()
    }

    // get data from the remove server
    private  fun  getData() {
        viewModelScope.launch {
            chatRepository.getListChat()
        }
    }
}