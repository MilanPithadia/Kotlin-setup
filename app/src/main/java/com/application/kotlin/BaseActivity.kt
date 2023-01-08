package com.application.kotlin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.application.kotlin.utils.Const
import com.application.kotlin.utils.PreferenceHelper
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity(), View.OnClickListener {


    @Inject
    lateinit var preferenceHelper: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)

    }

    fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun showSnack(msg: String, onTab: (() -> Unit)? = null) {

        Snackbar.make(window.decorView, msg, Snackbar.LENGTH_LONG).show()
    }

    override fun onClick(p0: View?) {
        onClickView(p0!!)
    }

    override fun onResume() {
        super.onResume()
        Const.TOKEN = preferenceHelper.get(Const.Param.TOKEN)
    }

    abstract fun onClickView(v: View)
}