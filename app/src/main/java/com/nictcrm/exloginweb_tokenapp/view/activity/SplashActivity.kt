package com.nictcrm.exloginweb_tokenapp.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.nictcrm.exloginweb_tokenapp.R
import com.nictcrm.exloginweb_tokenapp.base.BaseActivity
import com.nictcrm.exloginweb_tokenapp.databinding.ActivitySplashBinding


class SplashActivity : BaseActivity() {
    var TAG = this@SplashActivity.javaClass.simpleName

    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        Handler().postDelayed({
            Log.d(TAG, " print_log onCreate")
            val intent = Intent(this@SplashActivity, LoginViewActivity::class.java)    //MainCategoryActivity
            startActivity(intent)
            finish()
        }, SPLASH_DELAY.toLong())
    }

    companion object {
        private const val SPLASH_DELAY = 3000 // 2 seconds  2000
    }

}