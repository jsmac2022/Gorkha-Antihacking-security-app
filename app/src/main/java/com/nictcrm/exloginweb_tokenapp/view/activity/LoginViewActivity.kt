package com.nictcrm.exloginweb_tokenapp.view.activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber
import com.nictcrm.exloginweb_tokenapp.R
import com.nictcrm.exloginweb_tokenapp.base.BaseActivity
import com.nictcrm.exloginweb_tokenapp.data.api.RetrofitService


import com.nictcrm.exloginweb_tokenapp.data.repository.LoginViewRepository
import com.nictcrm.exloginweb_tokenapp.data.response.LoginResponse
import com.nictcrm.exloginweb_tokenapp.databinding.ActivityLoginviewBinding
import com.nictcrm.exloginweb_tokenapp.request.LoginRequest
import com.nictcrm.exloginweb_tokenapp.utils.AppConstant.token
import com.nictcrm.exloginweb_tokenapp.utils.CustomProgressDialogue
import com.nictcrm.exloginweb_tokenapp.utils.Utils.Companion.isPhoneNumberValid
import com.nictcrm.exloginweb_tokenapp.viewmodel.LoginViewModel
import com.nictcrm.exloginweb_tokenapp.viewmodelfactory.LoginViewModelFactory


class LoginViewActivity : BaseActivity() {
    var TAG = this@LoginViewActivity.javaClass.simpleName

    lateinit var binding: ActivityLoginviewBinding
    lateinit var  viewModel : LoginViewModel
    private val retrofitService = RetrofitService.getInstance()
    var loginResponse : LoginResponse? = null
    lateinit var progress: CustomProgressDialogue
    private val phoneNumberUtil = PhoneNumberUtil.getInstance()

    private lateinit var mobileNumberEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var signUpButton: Button
    var isConnected: Boolean = false

    companion object{
        lateinit var mSearchCusMobile: String
        lateinit var mSearchCusPassword: String
        lateinit var authToken: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_loginview)

        binding.layoutToolbar.toolbarTitle.text = "Login"
        binding.layoutToolbar.ivBackbutton.visibility = View.GONE
        binding.layoutToolbar.ivBackbutton.setOnClickListener {
            finish()
        }

        progress = CustomProgressDialogue(this@LoginViewActivity)

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
        binding.etEmailMobile.filters = arrayOf(InputFilter.LengthFilter(mNumberLength))


        ///api calling -->
       /* viewModel = ViewModelProvider(this, LoginViewModelFactory(LoginViewRepository(retrofitService))).get(LoginViewModel::class.java)
        viewModel.loginresponse.observe(this, Observer {
            Log.d(TAG, " print_log response: $it")
            var ProfileSave  = it
            try {
                progress.dismiss()
            }catch (e: Exception){}
            var IsSuccess = ProfileSave?.statusCode

            Log.d(TAG, " print_log IsSuccess: $IsSuccess")
            if(ProfileSave?.statusCode == 200){
//                Toast.makeText(this@LoginViewActivity, it, Toast.LENGTH_LONG).show()
                val intent = Intent(this@LoginViewActivity, HomeViewActivity::class.java)
//                val bundle = Bundle()
//                    bundle.putSerializable("leadStatusList", loginResponse)
//                    intent.putExtras(bundle)
                startActivity(intent)
                Log.d(TAG, " print_log userlogin response: $it")
            }else{
                Log.d(TAG, " print_log user login error: $it")
            }

            Log.d(TAG, " print_log loginResponse: "+loginResponse)
        })

        viewModel.errorMessage.observe(this, Observer {
            try {
                progress.dismiss()
            }catch (e: Exception){}
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()

            Log.d(TAG, " print_log errorMessage: $it")
        })
        Log.d(TAG, " print_log viewModel.getAllProductList()")*/


        binding.btnLoginSubmit.setOnClickListener {
            if(checkLoginValidDetails()== true) {
                var intent = Intent(this@LoginViewActivity, HomeViewActivity::class.java)
                Toast.makeText(this, "User Login Successfully", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                Log.d(TAG, " print_log Dashbaord : ")
            }else{
//                checkValidDetails()
                Log.d(TAG, " something went wrong")
            }

            /*if(binding.etEmailMobile.text.toString().isEmpty()){
                Toast.makeText(this, "Please Enter Mobile No.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if(binding.etPassword.text.toString().isEmpty()){
                Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }*/

//            userLogin()

        }

        binding.buttonSignup.setOnClickListener {
            val intent = Intent(this@LoginViewActivity, SignupViewActivity::class.java)
            startActivity(intent)
        }

    }

    private fun checkLoginValidDetails(): Boolean{
        var isMobileValid : Boolean = false;

        var countryCode = "+91"
        var mobileNumber = binding.etEmailMobile.text.toString()
        var fullMobileNumber = countryCode + mobileNumber

        try {
            isMobileValid = isPhoneNumberValid(fullMobileNumber, countryCode)
        }catch (e: Exception){
            Log.d(TAG, " print_log isMobileValid Exception : "+e.toString())
        }
        Log.d(TAG, " print_log isMobileValid : "+isMobileValid)

        if (binding.etEmailMobile.text!!.isEmpty()){
            Toast.makeText(this@LoginViewActivity, "Please Enter Mobile no.", Toast.LENGTH_SHORT).show()
            return false
        }else if (isMobileValid == false){
            Toast.makeText(this@LoginViewActivity, "Please Enter valid Mobile no.", Toast.LENGTH_SHORT).show()
            return false
        }else if(binding.etPassword.text!!.isEmpty()){
            Toast.makeText(this@LoginViewActivity, "Please Enter Your Password", Toast.LENGTH_SHORT).show()
            return false
        }
        return true

    }


    fun userLogin(){
        mSearchCusMobile = ""
        mSearchCusPassword = ""
        authToken = ""

        mSearchCusMobile = binding.etEmailMobile.text.toString()
        mSearchCusPassword = binding.etPassword.text.toString()
        authToken = token.toString()


        var loginRequest = LoginRequest(
            binding.etEmailMobile.text.toString(),
            binding.etPassword.text.toString(),
            token.toString()
        )


//        var intent = Intent(this@LoginViewActivity, HomeViewActivity::class.java)
//            val bundle = Bundle()
//            bundle.putSerializable("leadStatusList", intent.toString())
//            intent.putExtras(bundle)
//            startActivity(intent)

//        viewModel.getLogin(loginRequest)

        /*if(isConnected == true) {
            progress.show()
            viewModel.getLogin(loginRequest)
        }else{
            Toast.makeText(this,resources.getString(R.string.dialog_no_internet_error_msg),Toast.LENGTH_SHORT).show()
        }*/

    }

    fun noDataFound(){
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@LoginViewActivity)
        alertDialog.setTitle("")
        alertDialog.setMessage(resources.getString(R.string.lbl_no_data_found))
        alertDialog.setCancelable(true)
        alertDialog.setPositiveButton("OK",
            DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
        alertDialog.create().show()
    }


}