package com.teemy.mvvmretrofitkotlin.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.teemy.mvvmretrofitkotlin.R
import com.teemy.mvvmretrofitkotlin.adapter.UserAdapter
import com.teemy.mvvmretrofitkotlin.model.User
import com.teemy.mvvmretrofitkotlin.resource.ResourceState
import com.teemy.mvvmretrofitkotlin.utils.AppConstants
import com.teemy.mvvmretrofitkotlin.utils.Utils
import com.teemy.mvvmretrofitkotlin.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        initView()
        initViewModel()
    }

    private fun initView() {
        rvUsers.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter()
        rvUsers.adapter = userAdapter
    }

    private fun initViewModel() {
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        observeViewModel()
    }

    private fun observeViewModel() {
        userViewModel.getUsers().observe(this, Observer { responseState ->

            when (responseState.state) {

                ResourceState.LOADING -> {
                    pbUser.visibility = View.VISIBLE
                }
                ResourceState.SUCCESS -> {
                    pbUser.visibility = View.GONE
                    setDataToAdapter(responseState.data)
                }
                ResourceState.ERROR -> {
                    pbUser.visibility = View.GONE
                    Utils.showToast(this, AppConstants.NetworkError)
                }

            }

        })
    }

    private fun setDataToAdapter(data: ArrayList<User>?) {
        userAdapter.addItems(data)
    }
}