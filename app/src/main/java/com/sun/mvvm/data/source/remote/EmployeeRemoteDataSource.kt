package com.sun.mvvm.data.source.remote

import com.sun.mvvm.data.model.EmployeeDBResponse
import com.sun.mvvm.data.source.remote.api.EmployeeDataService
import com.sun.mvvm.data.source.EmployeeDataSource
import io.reactivex.Observable

class EmployeeRemoteDataSource(private val employeeDataService: EmployeeDataService) :
    EmployeeDataSource.Remote {

    override fun getEmployees(): Observable<EmployeeDBResponse> =
        employeeDataService.employees
}
