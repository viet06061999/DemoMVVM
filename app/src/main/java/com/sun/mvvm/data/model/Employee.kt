package com.sun.mvvm.data.model

import com.google.gson.annotations.SerializedName

data class Employee(
    @SerializedName(KEY_AVATAR)
    var avatar: String,
    @SerializedName(KEY_EMAIL)
    var email: String,
    @SerializedName(KEY_FIRST_NAME)
    var firstName: String,
    @SerializedName(KEY_ID)
    var id: Long,
    @SerializedName(KEY_LAST_NAME)
    var lastName: String
) {
    companion object {
        private const val KEY_AVATAR = "avatar"
        private const val KEY_EMAIL = "email"
        private const val KEY_FIRST_NAME = "first_name"
        private const val KEY_ID = "id"
        private const val KEY_LAST_NAME = "last_name"
    }
}
