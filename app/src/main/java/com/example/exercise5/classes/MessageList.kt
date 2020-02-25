package com.example.exercise5.classes

import kotlinx.serialization.UnstableDefault

/**
 *
 * MessageList-singleton holds messageList that is a mutableList of Strings
 *
 * This is the list we use to show the data from ChatServer
 *
 */

object MessageList{

    private var messageList = mutableListOf<String>()

    @UnstableDefault
    fun addMessage(item: String){
        messageList.add(item)
    }

    fun getMessages():List<String>{
        return messageList
    }
}