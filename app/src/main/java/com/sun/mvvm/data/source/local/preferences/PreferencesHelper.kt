package com.sun.mvvm.data.source.local.preferences

import com.sun.mvvm.data.model.User

interface PreferencesHelper {

    fun setCurrentUser(user: User)
    fun getCurrentUser(): User
    fun clearUser()
}
