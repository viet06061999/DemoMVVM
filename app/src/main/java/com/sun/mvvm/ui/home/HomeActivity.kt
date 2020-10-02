package com.sun.mvvm.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import com.sun.mvvm.base.BindingActivity
import com.sun.mvvm.R
import com.sun.mvvm.databinding.ActivityHomeBinding
import com.sun.mvvm.utils.createProgressDialog
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BindingActivity<ActivityHomeBinding>() {

    @LayoutRes
    override fun getLayoutResId(): Int = R.layout.activity_home

    private val employeeViewModel: EmployeeViewModel by viewModel()

    private val employeeDataAdapter = EmployeeDataAdapter {
        showToastMessage(it.lastName)
    }

    private val progressBar by lazy {
        createProgressDialog()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewEmployees.adapter = employeeDataAdapter
        observe()
    }

    private fun observe() {
        employeeViewModel.isLoading.observe(this, Observer {
            if (it) progressBar.show() else progressBar.dismiss()
        })
        employeeViewModel.allEmployee.observe(this, Observer {
            employeeDataAdapter.submitList(it)
        })
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }
}
