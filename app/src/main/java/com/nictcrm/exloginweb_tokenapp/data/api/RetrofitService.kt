package com.nictcrm.exloginweb_tokenapp.data.api


import com.nictcrm.exloginweb_tokenapp.data.response.LoginResponse
import com.nictcrm.exloginweb_tokenapp.request.LoginRequest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit


interface RetrofitService {

//    @GET("HDFCBank/webapi/hdfcapi/list")
//    fun getAllProductList() : Call<ProductList?>

    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance() : RetrofitService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(50, TimeUnit.SECONDS)
                .addInterceptor(interceptor).build()

            if (retrofitService == null) {//15
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://meraloha.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }// .client(client)

            return retrofitService!!
        }
    }

    //Login Api
    @POST("api/user_login")
    fun getLogin(@Body request: LoginRequest?): Call<LoginResponse>


}