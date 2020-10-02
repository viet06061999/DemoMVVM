package com.sun.mvvm.data.model

import com.google.gson.annotations.SerializedName

data class EmployeeDBResponse(
    @SerializedName("page")
    var page: Int,
    @SerializedName("per_page")
    var perPage: Int,
    @SerializedName("total")
    var total: Int,
    @SerializedName("total_pages")
    var totalPages: Int,
    @SerializedName("data")
    var employee: List<Employee>? = null
) {
    companion object {
        private const val KEY_PAGE = "page"
        private const val KEY_PER_PAGE = "per_page"
        private const val KEY_TOTAL = "total"
        private const val KEY_TOTALS_PAGE = "total_pages"
        private const val KEY_DATA = "data"
    }
}
