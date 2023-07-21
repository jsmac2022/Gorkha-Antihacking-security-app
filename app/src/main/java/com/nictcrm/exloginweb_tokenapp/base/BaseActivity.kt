package com.nictcrm.exloginweb_tokenapp.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    var TAG_BaseActivity = this@BaseActivity.javaClass.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG_BaseActivity, "print_log onCreate")
    }
}