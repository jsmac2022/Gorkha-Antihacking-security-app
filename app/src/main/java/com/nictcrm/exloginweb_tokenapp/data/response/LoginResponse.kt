package com.nictcrm.exloginweb_tokenapp.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse (

    @SerializedName("statusCode")
    val statusCode: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: List<Data>

)

data class Data (
    val id: Long,
    val profileDetailsStatus: String,
    val companyNameSts: String,
    val name: String,
    val email: String,
    val code: String,
    val mobile_number: Long,
    val dob: String,
    val gender: String,
    val latitude: String,
    val longitude: String,
    val locality: String,
    val tagline: String,
    val emailVerifiedAt: Any? = null,
    val status: Long,
    val country: Long,
    val state: Long,
    val city: Long,
    val pinCode: Any? = null,
    val userType: Long,
    val subCat: Any? = null,
    val referalCode: String,
    val referalFrom: Any? = null,
    val accountName: String,
    val accountNumber: Long,
    val bankName: String,
    val branchName: String,
    val ifscCode: String,
    val image: Any? = null,
    val createdAt: String,
    val updatedAt: String,
    val facebookID: Any? = null,
    val googleID: Any? = null,
    val linkedinID: Any? = null,
    val district: Any? = null,
    val tehsil: Any? = null,
    val isDeleted: Long,
    val profilePhoto: String,
    val profession: String,
    val socialMedia: String,
    val accepted: String,
    val token: Any? = null,
    val rankAmount: Any? = null,
    val since: String
)




