package com.sgncreations.sgnchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.sgncreations.sgnchat.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

//    private lateinit var binding: ActivityRegisterBinding
    private lateinit var regName: EditText
    private lateinit var regEmail: EditText
    private lateinit var regPassword: EditText
    private lateinit var register: TextView
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance()
//        mAuth.createUserWithEmailAndPassword("sree@gmail.com", "sree1")

        regName = findViewById(R.id.et_reg_name_input)
        regEmail = findViewById(R.id.et_reg_email_input)
        regPassword = findViewById(R.id.et_reg_pwd_input)
        register = findViewById(R.id.btn_register)

        supportActionBar?.hide()

        register.setOnClickListener {
            val name = regName.text.toString()
            val email = regEmail.text.toString()
            val password = regPassword.text.toString()
            if(!name.isNullOrEmpty() && !email.isNullOrEmpty() && !password.isNullOrEmpty()) {
                registerUser(name, email, password)
            }
        }

    }

    private fun registerUser(name: String, email: String, password: String) {
//        Toast.makeText(this,"Could not register $email ${mAuth.currentUser?.uid}",Toast.LENGTH_LONG).show()
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                  addUserToDatabase(name, email, mAuth.currentUser!!.uid)
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()

                } else {
                    Toast.makeText(this,"Could not register $email ${mAuth.currentUser?.uid}",Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun addUserToDatabase(name: String, email: String, uid: String?) {
        mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid!!).setValue(User(name, email, uid))
    }
}