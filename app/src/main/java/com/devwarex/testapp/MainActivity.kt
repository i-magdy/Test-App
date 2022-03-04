package com.devwarex.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.devwarex.testapp.models.UserModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val auth = Firebase.auth
        val currentUser = auth.currentUser
        if (currentUser == null){
            val signupIntent = Intent(this,SignUpActivity::class.java)
            startActivity(signupIntent)
            finish()
        }else{
           getUserById(currentUser.uid)
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