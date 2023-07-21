package com.nictcrm.exloginweb_tokenapp.view.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil

import com.nictcrm.exloginweb_tokenapp.R
import com.nictcrm.exloginweb_tokenapp.base.BaseActivity
import com.nictcrm.exloginweb_tokenapp.data.api.RetrofitService
import com.nictcrm.exloginweb_tokenapp.databinding.AcitivityHomeviewBinding
import com.nictcrm.exloginweb_tokenapp.utils.CustomProgressDialogue
import com.nictcrm.exloginweb_tokenapp.viewmodel.HomeViewModel


class HomeViewActivity : BaseActivity(){
    var TAG = this@HomeViewActivity.javaClass.simpleName

    lateinit var binding: AcitivityHomeviewBinding
    lateinit var viewModel: HomeViewModel
    private val retrofitService = RetrofitService.getInstance()
    private val tagLog = javaClass.simpleName as String
    lateinit var progress: CustomProgressDialogue

    var isConnected: Boolean = false
    var isMainCatFlag: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.acitivity_homeview)
        Log.d(TAG, " print_log onCreate")

        binding.layoutToolbar.toolbarTitle.text = "Gorkha Dashboard"
        binding.layoutToolbar.ivBackbutton.visibility = View.GONE
        binding.layoutToolbar.ivBackbutton.setOnClickListener {
            finish()
        }

        progress = CustomProgressDialogue(this@HomeViewActivity)


        /*binding.LoansButton.setOnClickListener {
            val intent = Intent(this@MainCategoryActivity, SavingsActivity::class.java)
            startActivity(intent)
        }*/
    }

}