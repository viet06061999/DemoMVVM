package com.sun.mvvm.data.source

import com.sun.mvvm.data.model.EmployeeDBResponse
import io.reactivex.Observable

interface EmployeeDataSource {

    interface Remote {
        fun getEmployees(): Observable<EmployeeDBResponse>
    }
}
