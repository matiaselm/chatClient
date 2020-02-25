package com.example.exercise5.classes

/**
 * Only the functions needed for implementing Observer-functionality to ChatClient
 *
 */

interface Observable {
    fun register(who: ServerObserver)
    fun deregister(who: ServerObserver)
}

interface ServerObserver{
    fun update()
}