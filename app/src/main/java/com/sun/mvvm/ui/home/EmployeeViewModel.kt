package com.sun.mvvm.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.mvvm.base.RxViewModel
import com.sun.mvvm.data.model.Employee
import com.sun.mvvm.data.repository.EmployeeRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EmployeeViewModel(
    private val employeeRepository: EmployeeRepository
) : RxViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _allEmployees = MutableLiveData<List<Employee>>()
    val allEmployee: LiveData<List<Employee>>
        get() = _allEmployees

    init {
        getEmployee()
    }

    private fun getEmployee() {
        _isLoading.value = true
        employeeRepository.getEmployees()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _allEmployees.postValue(it.employee) },
                { _isLoading.value = false },
                { _isLoading.value = false }
            ).let { addToDisposable(it) }
    }
}
