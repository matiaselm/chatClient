package com.example.exercise5.classes

import android.util.Log
import android.widget.Toast

import kotlinx.android.synthetic.main.listlayout.view.*
import java.io.PrintWriter
import java.lang.Exception
import java.net.Socket
import java.util.*

/**
 * ServerConnector is a runnable and observable class that runs mainly with different Threads
 *
 * Whenever we start ServerConnector it starts the First Thread that initializes the socket we use to connect to ChatServer
 * In the mainThread we start 2 different Threads for both incoming and outgoing data,
 * they both run in an endless loop listening the traffic between our client and ChatServer.
 *
 * Whenever there's a change in data, ServerConnector notifies Observer-MainActivity so there's no delay in receiving messages to RecyclerView
 *
 * Client sends data only as a one String that only has our input/message.
 * In the server's end it gets converted into a ChatMessage type (name: "Message" (timeStamp)) so we see it as that in our MainActivity
 */


class ServerConnector(private val ip: String,private val port: Int):Runnable, Observable{

    private lateinit var socket: Socket

    private var observers: MutableSet<ServerObserver> = mutableSetOf()

    override fun run(){
    Log.d("MessageList", "ServerConnector.run()")

    Thread{
        Log.d("MessageList", "MainThread started")
        socket = Socket(ip, port)

        Log.d("MessageList","Socket")
        val inpStream = socket.getInputStream()
        val scan = Scanner(inpStream)
        val out = PrintWriter(socket.getOutputStream(), true)

        val inputThread = Thread{
            while(true){
                Log.d("MessageList", "while(true)")
                try {

                    val input = scan.nextLine()
                    Log.d("MessageList", input.javaClass.typeName)
                    Log.d("MessageList", input)

                    MessageList.addMessage(input)
                    observers.forEach{it.update()}

                }catch (e: Exception){
                    Log.d("MessageList","$e")
                    continue
                }
            }
        }

        val outPutThread = Thread {
            while(true) {
                if (UserInputMessage.newMessage) {
                    Log.d(
                        "MessageList",
                        "outPutThread, printing line: ${UserInputMessage.getMessage()}"
                    )
                    observers.forEach{it.update()}

                    out.println(UserInputMessage.getMessage())
                    out.flush()

                    UserInputMessage.newMessage = false

                }
            }
        }

        out.println(User.getName())

        inputThread.start()
        outPutThread.start()

        }.start()
    }

    override fun register(who: ServerObserver) {
        observers.add(who)
        Log.d("ObserverList",observers.toString())
    }

    override fun deregister(who: ServerObserver) {
        observers.remove(who)
    }

}