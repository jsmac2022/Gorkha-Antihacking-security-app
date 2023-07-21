package com.nictcrm.exloginweb_tokenapp.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nictcrm.exloginweb_tokenapp.data.repository.LoginViewRepository
import com.nictcrm.exloginweb_tokenapp.viewmodel.LoginViewModel

class LoginViewModelFactory constructor(private val repository: LoginViewRepository):
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            LoginViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("LoginViewModel Not Found")
        }
    }
}