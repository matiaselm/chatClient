package com.example.exercise5.activities


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exercise5.R
import com.example.exercise5.classes.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.serialization.UnstableDefault

class MainActivity : ServerObserver, AppCompatActivity() {

    /**
     * In MainActivity we
     *  - initialize the adapter for the RecyclerView
     *  - Assign the userName-textField so it is shown to the user correctly
     *  - Start the ServerConnector -class that does the most important parts
     *  - And register MainActivity as an observer to the ServerConnector so we can update our recyclerView whenever the list holding server's messages gets updated
     *
     */

    lateinit var myAdapter: MyRecyclerViewAdapter

    @UnstableDefault
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        theList.layoutManager = LinearLayoutManager(this)

        myAdapter = MyRecyclerViewAdapter(this, MessageList.getMessages())
        theList.adapter = myAdapter

        editMessage.text.clear()
        textUserName.text = User.getName()

        val serverConnector = ServerConnector("10.0.2.2", 34300)

        serverConnector.run()
        serverConnector.register(this)

        /**
         * When sendBtn is pressed it takes the text written in editMessage-field and puts it into a singleton
         * This singleton is UserInputMessage. It holds 2 different variables, one for the actual String and a Boolean: newMessage.
         *
         * Whenever newMessage === true, ServerConnector sends the message in UserInputMessage to ChatServer
         */

        sendBtn.setOnClickListener {
            val msgText = editMessage.text.toString()

            UserInputMessage.setMessage(msgText)
            UserInputMessage.newMessage = true

            myAdapter.notifyDataSetChanged()
            editMessage.text.clear()
        }
    }

    override fun update(){
        Log.d("ObserverList","fun update")
        runOnUiThread { myAdapter.notifyDataSetChanged() }
    }
}