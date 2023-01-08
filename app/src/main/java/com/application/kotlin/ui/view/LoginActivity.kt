package com.application.kotlin.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.application.kotlin.BaseActivity
import com.application.kotlin.R
import com.application.kotlin.data.remote.ApiCallback
import com.application.kotlin.databinding.ActivityLoginBinding
import com.application.kotlin.ui.viewmode.LoginViewModel
import com.application.kotlin.utils.Const
import com.application.kotlin.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener(this)
    }

    override fun onClickView(v: View) {
        when (v.id) {
            R.id.btnLogin -> {
                if (binding.etName.text.toString().isEmpty()) {
                    showMessage("Please enter UserName")
                } else if (binding.etPassword.text.toString().isEmpty()) {
                    showMessage("Please enter Password")
                } else {
                    login()
                }
            }
        }
    }


    private fun login() {
        Utils.showProgress(this)
        val json = with(JSONObject()) {
            put(Const.Param.USERNAME, binding.etName.text.toString())
            put(Const.Param.PASSWORD, binding.etPassword.text.toString())
            put(Const.Param.DEVICE_TOKEN, preferenceHelper.get(Const.Param.DEVICE_TOKEN))
        }
        viewModel.login(Utils.makeJSONRequestBody(json)).observe(this) {
            Utils.hideProgress()
            when (it) {
                is ApiCallback.OnSuccess -> {
                    if (it.data != null) {

                        if (it.data.status) {
                            preferenceHelper.set(Const.Param.USERID,it.data.userId)
                            preferenceHelper.set(Const.Param.TOKEN,it.data.token)
                            startActivity(Intent(applicationContext, MainActivity::class.java))
                            finish()
                        } else {
                            showMessage(it.data.message)
                        }
                    }
                }
                is ApiCallback.OnError -> {
                    showMessage(it.message.toString())
                }
            }
        }
    }
}