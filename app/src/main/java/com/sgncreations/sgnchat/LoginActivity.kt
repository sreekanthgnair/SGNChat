package com.sgncreations.sgnchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.sgncreations.sgnchat.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

//    private lateinit var binding: ActivityLoginBinding
    private lateinit var logEmail: EditText
    private lateinit var logPassword: EditText
    private lateinit var login: Button
    private lateinit var register: TextView
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_login)

        logEmail = findViewById(R.id.et_email_input)
        logPassword = findViewById(R.id.et_pwd_input)
        login = findViewById(R.id.btn_login)
        register = findViewById(R.id.tv_register_txt)
        mAuth = FirebaseAuth.getInstance()

        supportActionBar?.hide()

        login.setOnClickListener{
            val email = logEmail.text.toString()
            val password = logPassword.text.toString()
            if(!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
                loginUser(email, password)
            }

        }

        register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

    }

    private fun loginUser(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "User logged in", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()


                } else {
                    Toast.makeText(this, "Cannot log in", Toast.LENGTH_SHORT).show()
                }
            }
    }
}