package com.example.chatapp.presentation.screen.home.messages.createMessage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatapp.domain.core.base.BaseViewModel
import com.example.chatapp.domain.model.Friend

class CreateMessageViewModel : BaseViewModel() {
    private val _chosenFriendList = MutableLiveData<ArrayList<Friend>>()
    val chosenFriendList: LiveData<ArrayList<Friend>> get() = _chosenFriendList

    fun setFriendList(chosenFriendList: ArrayList<Friend>) {
        _chosenFriendList.postValue(chosenFriendList)
    }

}