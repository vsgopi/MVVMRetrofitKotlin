package com.teemy.mvvmretrofitkotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.teemy.mvvmretrofitkotlin.extensions.setError
import com.teemy.mvvmretrofitkotlin.extensions.setLoading
import com.teemy.mvvmretrofitkotlin.extensions.setSuccess
import com.teemy.mvvmretrofitkotlin.model.User
import com.teemy.mvvmretrofitkotlin.network.ApiClient
import com.teemy.mvvmretrofitkotlin.network.ApiInterface
import com.teemy.mvvmretrofitkotlin.resource.Resource
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val userResp = MutableLiveData<Resource<ArrayList<User>>>()


    fun apiResp() {
        viewModelScope.launch {
            userResp.setLoading()
            val apiInterface: ApiInterface = ApiClient.getClient().create(ApiInterface::class.java)
            val apiResp: Call<ArrayList<User>> = apiInterface.getUsers()
            val enqueueObject = object : Callback<ArrayList<User>> {
                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    userResp.setError(t.message)
                }
                override fun onResponse(
                    call: Call<ArrayList<User>>,
                    response: Response<ArrayList<User>>
                ) {
                    userResp.setSuccess(response.body())
                }
            }
            apiResp.enqueue(enqueueObject)
        }
    }


    //fetching the data and passing the data to activity using liveData
    fun getUsers(): LiveData<Resource<ArrayList<User>>> {
        apiResp()
        return userResp
    }
}