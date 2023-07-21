package com.nictcrm.exloginweb_tokenapp.view.activity

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber
import com.nictcrm.exloginweb_tokenapp.R
import com.nictcrm.exloginweb_tokenapp.base.BaseActivity
import com.nictcrm.exloginweb_tokenapp.databinding.ActivitySignupBinding
import com.nictcrm.exloginweb_tokenapp.utils.Utils

class SignupViewActivity : BaseActivity() {
    var TAG = this@SignupViewActivity.javaClass.simpleName
    lateinit var binding: ActivitySignupBinding
    private val phoneNumberUtil = PhoneNumberUtil.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)

        binding.layoutToolbar.toolbarTitle.text = "SignUp"
        binding.layoutToolbar.ivBackbutton.setOnClickListener {
            finish()
        }

        //for mobile number valid
        var phoneNumberType = PhoneNumberUtil.PhoneNumberType.MOBILE
        var exampleNumber: Phonenumber.PhoneNumber = phoneNumberUtil.getExampleNumberForType(
            "IN",
            phoneNumberType
        )
        var formattedNumber: String = phoneNumberUtil.format(exampleNumber, PhoneNumberUtil.PhoneNumberFormat.NATIONAL)
        Log.d(TAG, " print_log formattedNumber : "+formattedNumber)

        var mNumberLength: Int
        var getNumber: String = formattedNumber.replace("\\s".toRegex(), "")
        Log.d(TAG, " print_log getNumber : "+getNumber)
        mNumberLength = getNumber.length
        Log.d(TAG, " print_log mNumberLength : "+mNumberLength)

        var firstChar = getNumber.first()
        Log.d(TAG, " print_log firstChar : "+firstChar)
        if (firstChar.toString().equals("0")){
            var getFullNumber = getNumber.substring(1)
            Log.d(TAG, " print_log getFullNumber : "+getFullNumber)
            mNumberLength = getFullNumber.length
        }
        Log.d(TAG, " print_log final mNumberLength : "+mNumberLength)
        binding.etMobile.filters = arrayOf(InputFilter.LengthFilter(mNumberLength))


        binding.btnSignupSubmit.setOnClickListener {
            if(checkSignupValidDetails()== true) {
                val intent = Intent(this@SignupViewActivity, LoginViewActivity::class.java)
                Toast.makeText(this, "User Signup Successfully.", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                Log.d(TAG, " print_log signup if : "+intent)
            }else{
                Log.d(TAG, "print_log signup else: something went wrong")
            }
        }

        binding.buttonLogin.setOnClickListener {
            val intent = Intent(this@SignupViewActivity, LoginViewActivity::class.java)
            startActivity(intent)
        }
    }


    private fun checkSignupValidDetails(): Boolean{
        var isMobileValid : Boolean = false;

        var countryCode = "+91"
        var mobileNumber = binding.etMobile.text.toString()
        var fullMobileNumber = countryCode + mobileNumber

        try {
            isMobileValid = Utils.isPhoneNumberValid(fullMobileNumber, countryCode)
        }catch (e: Exception){
            Log.d(TAG, " print_log isMobileValid Exception : "+e.toString())
        }
        Log.d(TAG, " print_log isMobileValid : "+isMobileValid)


        if (binding.etFullName.text!!.isEmpty()){
            Toast.makeText(this@SignupViewActivity, "Please Enter Full Name.", Toast.LENGTH_SHORT).show()
            return false
        }
        else if(binding.etEmailID.text!!.isEmpty()){
            Toast.makeText(this@SignupViewActivity, "Please Enter Email", Toast.LENGTH_SHORT).show()
            return false
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(binding.etEmailID.text!!).matches()){  //else if(Patterns.EMAIL_ADDRESS.matcher(binding.etEmailId.text).matches() == false){
            Toast.makeText(this@SignupViewActivity, "Please Enter Valid Email Address", Toast.LENGTH_SHORT).show()
            return false
        }
        else if (binding.etMobile.text!!.isEmpty()){
            Toast.makeText(this@SignupViewActivity, "Please Enter Mobile no.", Toast.LENGTH_SHORT).show()
            return false
        }else if (isMobileValid == false){
            Toast.makeText(this@SignupViewActivity, "Please Enter valid Mobile no.", Toast.LENGTH_SHORT).show()
            return false
        }
        else if(binding.etPassword.text!!.isEmpty()){
            Toast.makeText(this@SignupViewActivity, "Please Enter Your Password", Toast.LENGTH_SHORT).show()
            return false
        }
        return true

    }

}