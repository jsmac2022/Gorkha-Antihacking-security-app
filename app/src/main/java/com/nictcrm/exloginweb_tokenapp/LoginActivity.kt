/*
package com.nictcrm.exloginweb_tokenapp

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.nictcrm.exloginweb_tokenapp.data.model.ApiService
import com.nictcrm.exloginweb_tokenapp.data.model.Constants
import com.nictcrm.exloginweb_tokenapp.data.model.LoginRequest
import com.nictcrm.exloginweb_tokenapp.data.model.LoginResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


class LoginActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        webView = findViewById(R.id.webview)
        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        webView.webViewClient = WebViewClient()
        // this will load the url of the website
        webView.loadUrl("https://meraloha.com/")
        // this will enable the javascript settings, it can also allow xss vulnerabilities
        webView.settings.javaScriptEnabled = true
        // if you want to enable zoom feature
        webView.settings.setSupportZoom(true)

        tokenSave()

        ///-->
      //  apiClient = ApiClient()
//        sessionManager = SessionManager(this)

//        apiClient.getApiService().login(LoginRequest())
//            .enqueue(object : Callback<LoginResponse> {
//                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                    // Error logging in
//                }
//
//                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//                    var loginResponse = response.body()
//
//                    if (response.isSuccessful && loginResponse?.token != null) {
//                        sessionManager.saveAuthToken(loginResponse.token)
//                    } else {
//                        // Error logging in
//                        Log.d("print error",loginResponse.toString())
//                    }
//                }
//            })

    }

    private fun tokenSave() {

        val okHttpClient: OkHttpClient? = getUnsafeOkHttpClient()

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)

        var loginRequest = LoginRequest("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVJZCI6Ijk1MTY1ODAyNTUiLCJpZCI6MjA1LCJyb2xlIjoic3R1ZGVudCIsInR5cGUiOiJtb2JpbGUiLCJpYXQiOjE2NjkwOTQ4MjgsImV4cCI6MTY3Njg3MDgyOH0.YrrXuokvCxgIjoEYKLc7JjY03DL_V5kogijirgULHVs",
            "9516580255")

        var retrofitData = retrofitBuilder.login(loginRequest)
        retrofitData.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                Log.d("LoginActivity", " onResponse: " + response)

                val responseBody = response.body()
                Log.d("LoginActivity", " responseBody: " + responseBody)

              //  Log.d("LoginActivity Message ", responseBody.Message)
             //   Log.d("Status ", responseBody.Status.toString())


                */
/*for (i in 0 until responseBody.Result.size) {
                    Log.d("CategName ", responseBody.Result[i].CategName)
                    Log.d("CurrencySymbol ", responseBody.Result[i].CurrencySymbol)
                }*//*


            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("LoginActivity", "OnFailure: " + t.message)

            }

        }
        )
    }

    fun getUnsafeOkHttpClient(): OkHttpClient? {
        return try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts =
                arrayOf<TrustManager>(
                    object : X509TrustManager {
                        @Throws(CertificateException::class)
                        override fun checkClientTrusted(
                            chain: Array<X509Certificate>,
                            authType: String
                        ) {
                        }

                        @Throws(CertificateException::class)
                        override fun checkServerTrusted(
                            chain: Array<X509Certificate>,
                            authType: String
                        ) {
                        }

                        override fun getAcceptedIssuers(): Array<X509Certificate> {
                            return arrayOf()
                        }
                    }
                )

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(
                sslSocketFactory,
                trustAllCerts[0] as X509TrustManager
            )
            builder.hostnameVerifier(HostnameVerifier { hostname, session -> true })
            builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }


    // if you press Back button this code will work for webview
    override fun onBackPressed() {
        if (webView.canGoBack())
            webView.goBack()
        else
            super.onBackPressed()
    }


}*/
