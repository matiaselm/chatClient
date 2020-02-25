package com.example.exercise5.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import android.content.Intent
import com.example.exercise5.R
import com.example.exercise5.classes.User

class LoginActivity : AppCompatActivity() {

    private lateinit var thisActivity: LoginActivity

    /**
     * In LoginActivity we ask the userName and put it into a singleton Object
     *
     * After ConfirmBtn is pressed, we continue in to the MainActivity and call finish() on this one so the user can't go back to change their username.
     * Going back and changing the username made some fun things with the server when it was possible, so this is only a quick and not very pretty fix
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.thisActivity = this

        var userName: String

        ConfirmBtn.setOnClickListener {
            userName = nameText.text.toString()
            User.setName(userName)

            val nextActivity = Intent(thisActivity, MainActivity::class.java)
            startActivity(nextActivity)
            finish()
        }
    }
}