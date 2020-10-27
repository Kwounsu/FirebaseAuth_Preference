package com.example.firebaseauthpreference

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btn_login
import kotlinx.android.synthetic.main.activity_main.btn_logout
import kotlinx.android.synthetic.main.activity_main.btn_register
import kotlinx.android.synthetic.main.activity_main.et_email
import kotlinx.android.synthetic.main.activity_main.et_password
import kotlinx.android.synthetic.main.activity_main.tv_message


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

        btn_preference.setOnClickListener {
            val intent = Intent (this, PreferenceActivity::class.java)
            startActivity(intent)
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
                    Toast.makeText(this@MainActivity, "Login Succeeded", Toast.LENGTH_SHORT).show()

                    tv_message.text = App.prefs.myEditText
                    et_email.text.clear()
                    et_password.text.clear()
                    btn_logout.visibility = View.VISIBLE
                    btn_login.visibility = View.GONE
                    btn_register.visibility = View.GONE
                    et_email.visibility = View.GONE
                    et_password.visibility = View.GONE
                } else {
                    Toast.makeText(this@MainActivity, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun signOut() {
        Firebase.auth.signOut()

        btn_logout.visibility = View.GONE
        btn_login.visibility = View.VISIBLE
        btn_register.visibility = View.VISIBLE
        et_email.visibility = View.VISIBLE
        et_password.visibility = View.VISIBLE

        tv_message.text = "Please Login"
    }
}