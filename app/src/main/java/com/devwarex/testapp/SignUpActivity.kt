package com.devwarex.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.devwarex.testapp.databinding.ActivitySignUpBinding
import com.devwarex.testapp.models.UserModel

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val auth =  Firebase.auth
        val db = Firebase.firestore
        binding.signUpEmailButton.setOnClickListener {
            val email = binding.editTextTextEmailAddress.editableText.toString()
            val password = binding.editTextTextPassword.editableText.toString()
            val name = binding.nameEditText.editableText.toString()
            Log.e("data",email.length.toString())
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        db.collection("users")
                            .add(UserModel(
                                name = name,
                                email = email,
                                uid = user?.uid ?: ""
                            )).addOnCompleteListener {
                                updateUi()
                                Log.d("database","user success creating..")
                            }

                    } else {
                        // If sign in fails, display a message to the user.

                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()

                    }
                }.addOnFailureListener { Log.e("faild",it.message!!) }

        }


        binding.toSignIn.setOnClickListener {
            val signInIntent = Intent(this,SignInActivity::class.java)
            startActivity(signInIntent)
            finish()
        }

    }

    private fun updateUi(){
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}