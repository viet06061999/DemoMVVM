package com.sun.mvvm.data.repository

import com.sun.mvvm.data.model.EmployeeDBResponse
import com.sun.mvvm.data.source.EmployeeDataSource
import io.reactivex.Observable

class EmployeeRepositoryImpl(private val employeeDataSource: EmployeeDataSource.Remote): EmployeeRepository {

    override fun getEmployees(): Observable<EmployeeDBResponse> =
        employeeDataSource.getEmployees()
}
