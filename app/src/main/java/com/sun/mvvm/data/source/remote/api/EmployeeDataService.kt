package com.sun.mvvm.data.source.remote.api

import com.sun.mvvm.data.model.EmployeeDBResponse
import com.sun.mvvm.utils.AppConstants.EMPLOYEE_END_POINT
import io.reactivex.Observable
import retrofit2.http.GET

interface EmployeeDataService {
    @get:GET(EMPLOYEE_END_POINT)
    val employees: Observable<EmployeeDBResponse>
}
