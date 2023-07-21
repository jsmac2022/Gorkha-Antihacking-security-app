package com.nictcrm.exloginweb_tokenapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nictcrm.exloginweb_tokenapp.MyApplication
import com.nictcrm.exloginweb_tokenapp.R


import com.nictcrm.exloginweb_tokenapp.data.repository.LoginViewRepository
import com.nictcrm.exloginweb_tokenapp.data.response.LoginResponse
import com.nictcrm.exloginweb_tokenapp.request.LoginRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel constructor(private var repository: LoginViewRepository) : ViewModel() {
    var TAG = this@LoginViewModel.javaClass.simpleName

    var loginresponse = MutableLiveData<LoginResponse?>()
    var errorMessage = MutableLiveData<String>()
    var context = MyApplication.appContext

    // Login api()
    fun getLogin( body : LoginRequest) {

        Log.d(TAG, " print_log getLogin")

        var response = repository.getLogin(body)

        response?.enqueue(object : Callback<LoginResponse?> {

            override fun onResponse(call: Call<LoginResponse?>, response: Response<LoginResponse?>) {

                Log.d(TAG, " print_log onResponse if response "+response)

                // var responseCode = response.code()
                if (response.code()== 200) {
                    loginresponse.postValue(response.body())

                }else if(response.code()==404) {
                    errorMessage.postValue(context.getString(R.string.error_msg_something_went_wrong))
                    Log.d(TAG, " print_log else if onResError "+response.code())
                }else {
                    errorMessage.postValue(context.getString(R.string.error_msg_something_went_wrong))
                    Log.d(TAG, " print_log else "+response)

                }
            }

            override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {

                Log.d(TAG, " print_log onFailure t "+t.message)
                errorMessage.postValue(t.message)
            }
        })

    }

}