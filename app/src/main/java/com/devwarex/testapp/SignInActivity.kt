package com.devwarex.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.devwarex.testapp.databinding.ActivitySignInBinding
import com.devwarex.testapp.models.UserModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val auth = Firebase.auth
        binding.signInEmailButton.setOnClickListener {
            val email = binding.editTextTextEmailAddress.editableText.toString()
            val password = binding.editTextTextPassword.editableText.toString()
            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        val user = auth.currentUser
                        getUserById(user?.uid ?: "")
                    }else{
                        Toast.makeText(this,"Failed ti sign in", Toast.LENGTH_LONG).show()
                    }
                }.addOnFailureListener { Log.e("sign_in",it.message!!) }

        }

        binding.toSignUp.setOnClickListener {
            val signUpIntent = Intent(this,SignUpActivity::class.java)
            startActivity(signUpIntent)
            finish()
        }
    }

    private fun getUserById(uid: String){
        val db = Firebase.firestore
        db.collection("users")
            .whereEqualTo("uid",uid)
            .get(Source.SERVER)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    if (task.result?.isEmpty!!){
                        Toast.makeText(this,"User not found!",Toast.LENGTH_LONG).show()
                    }else{
                        for (document in task.result!!.documents){
                            val user = document.toObject(UserModel::class.java)
                            if (user?.role == "customer"){
                                val homeIntent = Intent(this,HomeActivity::class.java)
                                startActivity(homeIntent)
                            }
                            if(user?.role == "admin"){
                                val adminIntent = Intent(this,AdminActivity::class.java)
                                startActivity(adminIntent)
                                finish()
                            }
                        }
                    }
                }
            }
    }
}