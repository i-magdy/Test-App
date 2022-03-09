package com.devwarex.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.devwarex.testapp.databinding.ActivityRecyclerBinding
import com.devwarex.testapp.models.UserModel

class RecyclerActivity : AppCompatActivity(), UsersAdapter.OnUserClick {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.usersRv.layoutManager = LinearLayoutManager(this)
        val adapter = UsersAdapter(this)
        binding.usersRv.adapter = adapter
        adapter.setUsersList(
            listOf(
                UserModel("ibrahim"),
                UserModel("Soha"),
                UserModel("Mohamed"),
                UserModel("Ahmed"),
                UserModel("ibrahim"),
                UserModel("ibrahim"),
                UserModel("Soha"),
                UserModel("Mohamed"),
                UserModel("Ahmed"),
                UserModel("ibrahim"),
                UserModel("ibrahim"),
                UserModel("Soha"),
                UserModel("Mohamed"),
                UserModel("Ahmed"),
                UserModel("ibrahim"),
                UserModel("ibrahim"),
                UserModel("Soha"),
                UserModel("Mohamed"),
                UserModel("Ahmed"),
                UserModel("ibrahim")
            )
        )
        binding.addUserFab.setOnClickListener { Toast.makeText(this,"add new user",Toast.LENGTH_SHORT).show() }
    }

    override fun onClick(position: Int) {
        Toast.makeText(this,"$position",Toast.LENGTH_SHORT).show()
    }
}