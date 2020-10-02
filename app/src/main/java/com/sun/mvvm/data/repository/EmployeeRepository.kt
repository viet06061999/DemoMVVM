package com.sun.mvvm.data.repository

import com.sun.mvvm.data.model.EmployeeDBResponse
import io.reactivex.Observable

interface EmployeeRepository {

    fun getEmployees(): Observable<EmployeeDBResponse>
}
