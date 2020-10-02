package com.sun.mvvm.data.source.local.preferences

import android.content.Context
import com.sun.mvvm.data.model.User

class PreferencesHelperImpl(context: Context) : PreferencesHelper {

    private val preferencesUser = context.getSharedPreferences(
        PREF_NAME_USER,
        Context.MODE_PRIVATE
    )
    private val editorUser = preferencesUser.edit()

    override fun setCurrentUser(user: User) {
        editorUser.apply {
            putInt(PREF_KEY_USER_ID, user.id)
            putString(PREF_KEY_USER_EMAIL, user.email)
            apply()
        }
    }

    override fun getCurrentUser(): User {
        preferencesUser.apply {
            return User(
                getInt(PREF_KEY_USER_ID, -1),
                getString(PREF_KEY_USER_EMAIL, "") ?: "",
                ""
            )
        }
    }

    override fun clearUser() {
        editorUser.apply {
            putInt(PREF_KEY_USER_ID, -1)
            putString(PREF_KEY_USER_EMAIL, "")
            apply()
        }
    }

    companion object {
        const val PREF_NAME_USER = "user"
        const val PREF_KEY_USER_ID = "user_id"
        const val PREF_KEY_USER_EMAIL = "email"
    }
}
