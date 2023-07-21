package com.nictcrm.exloginweb_tokenapp.utils

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import com.nictcrm.exloginweb_tokenapp.R

class CustomProgressDialogue(context: Context?) : Dialog(
    context!!
) {
    init {
        val wlmp = window!!.attributes
        wlmp.gravity = Gravity.CENTER_HORIZONTAL
        window!!.attributes = wlmp
        setTitle("")
        setCancelable(false)
        setOnCancelListener(null)
        val view: View = LayoutInflater.from(context).inflate(
            R.layout.custom_progress, null
        )
        setContentView(view)
    }
}