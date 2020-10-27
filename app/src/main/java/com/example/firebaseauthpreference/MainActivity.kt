package com.example.firebaseauthpreference

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        btn_register.setOnClickListener {
            createEmailAccount()
        }
        btn_login.setOnClickListener {
            startSignIn(et_email.text.toString(), et_password.text.toString())
        }
        btn_logout.setOnClickListener {
            signOut()
        }
    }

    private fun createEmailAccount() {
        val email = et_email.text.toString()
        val password = et_password.text.toString()
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()

                // Preference
                App.prefs.myEditText = email
            } else {
                Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()
            }
        }
    }

    public override fun onStart() {
        super.onStart()
    }

    private fun startSignIn(email: String, pwd: String) {
        auth.signInWithEmailAndPassword(email, pwd)
            .addOnCompleteListener(
                this@MainActivity
            ) { task ->
                if (task.isSuccessful) {
                    btn_logout.visibility = View.VISIBLE
                    btn_login.visibility = View.GONE
                    btn_register.visibility = View.GONE

                    // Preference
                    tv_message.text = App.prefs.myEditText
                } else {
                    Toast.makeText(this@MainActivity, "로그인 오류", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun signOut() {
        Firebase.auth.signOut()

        btn_logout.visibility = View.GONE
        btn_login.visibility = View.VISIBLE
        btn_register.visibility = View.VISIBLE

        tv_message.text = "Please Login"
    }

}