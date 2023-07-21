package com.nictcrm.exloginweb_tokenapp.request

data class LoginRequest (

    var emailOrPhone : String,
    var password : String,
    var token: String,

)