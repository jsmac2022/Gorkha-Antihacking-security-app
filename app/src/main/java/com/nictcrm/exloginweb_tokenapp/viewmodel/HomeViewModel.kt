package com.nictcrm.exloginweb_tokenapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nictcrm.exloginweb_tokenapp.MyApplication

import com.nictcrm.exloginweb_tokenapp.data.repository.LoginViewRepository
import com.nictcrm.exloginweb_tokenapp.data.response.LoginResponse

class HomeViewModel constructor(private var repository: LoginViewRepository) : ViewModel() {
    var TAG = this@HomeViewModel.javaClass.simpleName

    var leadStatus = MutableLiveData<LoginResponse?>()
    var errorMessage = MutableLiveData<String>()
    var context = MyApplication.appContext


}