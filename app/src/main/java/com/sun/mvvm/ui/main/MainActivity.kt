package com.sun.mvvm.ui.main

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import com.sun.mvvm.base.BindingActivity
import com.sun.mvvm.R
import com.sun.mvvm.databinding.ActivityMainBinding
import com.sun.mvvm.ui.home.HomeActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BindingActivity<ActivityMainBinding>() {

    @LayoutRes
    override fun getLayoutResId(): Int = R.layout.activity_main

    private val viewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        bind()
        observe()
    }

    private fun bind() {
        binding.mainVM = getViewModel()
    }

    private fun observe() {
        binding.buttonLogin.setOnClickListener {
            viewModel.signIn()
            viewModel.isUser.observe(this, Observer {
                if (it) {
                    startActivity(HomeActivity.getIntent(this))
                } else {
                    showToastMessage(R.string.msg_login_fail)
                }
            })
        }
    }
}
