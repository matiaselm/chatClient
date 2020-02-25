package com.example.exercise5.classes

/**
 * As ChatClient has only one user, I figured it was the easiest way to just store UserName into a singleton
 *
 * UserInputMessage holds the written message and a boolean that's used to check whenever there's new messages to be sent
 *
 * ChatClient doesn't utilize sharedPreferences or any other saving methods as I didn't have time to implement those.
 *
 * ChatServer however writes and remembers history so it's not so big of a deal for now
 */

object User{
    private lateinit var uName: String
    fun setName(name: String){
         uName = name
    }
    fun getName():String{
        return uName
    }
}

object UserInputMessage{
    private lateinit var message: String

    var newMessage: Boolean = false

    fun setMessage(msg: String){
        message = msg
    }

    fun getMessage():String{
        return message
    }
}