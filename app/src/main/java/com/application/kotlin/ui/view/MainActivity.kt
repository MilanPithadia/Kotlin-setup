package com.application.kotlin.ui.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.application.kotlin.BaseActivity
import com.application.kotlin.R
import com.application.kotlin.databinding.ActivityMainBinding
import com.application.kotlin.ui.viewmode.MainViewModel
import com.ketan.app.fragments.HomeFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.appBarMain.ivBack.visibility = View.GONE
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.frameLayout1, HomeFragment())
            .commit()

    }

    override fun onClickView(v: View) {

    }
}