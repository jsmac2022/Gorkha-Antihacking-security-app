package com.nictcrm.exloginweb_tokenapp.utils

import android.app.Dialog
import android.content.Context
import android.widget.ImageView
import android.widget.TextView


import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.nictcrm.exloginweb_tokenapp.R

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date


class Utils {
    companion object{

        //Mobile no validation
        fun isPhoneNumberValid(phoneNumber: String?, countryCode: String?): Boolean {
            // NOTE: This should probably be a member variable.
            val phoneUtil = PhoneNumberUtil.getInstance()
            try {
                val numberProto = phoneUtil.parse(phoneNumber, countryCode)
                return phoneUtil.isValidNumber(numberProto)
            } catch (e: NumberParseException) {
                System.err.println("NumberParseException was thrown: $e")
            }
            return false
        }


    }
}