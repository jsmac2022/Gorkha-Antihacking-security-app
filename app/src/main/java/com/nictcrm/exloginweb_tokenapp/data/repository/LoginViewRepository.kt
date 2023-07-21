package com.nictcrm.exloginweb_tokenapp.data.repository

import com.nictcrm.exloginweb_tokenapp.data.api.RetrofitService
import com.nictcrm.exloginweb_tokenapp.request.LoginRequest


class LoginViewRepository constructor(private var retrofitService: RetrofitService?)  {

    fun getLogin(body : LoginRequest) = retrofitService?.getLogin(body)
}