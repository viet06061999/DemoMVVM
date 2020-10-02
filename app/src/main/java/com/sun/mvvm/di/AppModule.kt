package com.sun.mvvm.di

import com.sun.mvvm.ui.home.EmployeeViewModel
import com.sun.mvvm.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { EmployeeViewModel(get()) }
}

