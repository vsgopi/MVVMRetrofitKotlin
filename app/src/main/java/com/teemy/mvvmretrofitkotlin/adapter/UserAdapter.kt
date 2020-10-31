package com.teemy.mvvmretrofitkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teemy.mvvmretrofitkotlin.R
import com.teemy.mvvmretrofitkotlin.model.User
import kotlinx.android.synthetic.main.row_user_item.view.*

class UserAdapter() : RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    var arrayList = ArrayList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_user_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        val item = arrayList[position]
        holder.bindData(item)
    }

    fun addItems(data: ArrayList<User>?) {
        if (data != null) {
            this.arrayList = data
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(item: User) {
            itemView.tvUserName.text = item.name
            itemView.tvUserEmail.text = item.email
        }
    }
}