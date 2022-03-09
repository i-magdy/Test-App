package com.devwarex.testapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devwarex.testapp.models.UserModel

class UsersAdapter(
    private val listener: OnUserClick
): RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {


    private var users: List<UserModel> = listOf()

    inner class UsersViewHolder(
        item: View
    ): RecyclerView.ViewHolder(item), View.OnClickListener{
        override fun onClick(p0: View?) {
            listener.onClick(adapterPosition)
        }

        private val name = item.findViewById<TextView>(R.id.user_item_name)
        fun onBind(user: UserModel){
            name.text = user.name
        }

        init {
          item.setOnClickListener(this)
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setUsersList(users: List<UserModel>){
        this.users = users
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder = UsersViewHolder(
        item = LayoutInflater.from(parent.context).inflate(R.layout.user_list_item,parent,false)
    )

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.onBind(user = users[position])
    }

    override fun getItemCount(): Int  = users.size

    interface OnUserClick{
        fun onClick(position: Int)
    }
}