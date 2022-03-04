package com.devwarex.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.devwarex.testapp.models.UserModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val db = Firebase.firestore
        db.collection("users")
            .get().addOnCompleteListener { task ->
                if (task.isSuccessful){
                    for (document in task.result?.documents!!){
                        val user = document.toObject(UserModel::class.java)
                        Log.e("user","${user?.name} ,email: ${user?.email}")
                    }
                }
            }
    }
}