package com.sun.mvvm.data.source.local

import com.sun.mvvm.data.model.User
import com.sun.mvvm.data.source.UserDataSource
import com.sun.mvvm.data.source.local.database.dao.UserDao
import com.sun.mvvm.data.source.local.preferences.PreferencesHelper
import io.reactivex.Single

class UserLocalDataSource(
    private val userDao: UserDao,
    private val pref: PreferencesHelper
) : UserDataSource.Local {

    override fun setCurrentUser(user: User) {
        pref.setCurrentUser(user)
    }

    override fun getCurrentUser(): Single<User> =
        Single.just(pref.getCurrentUser())

    override fun singIn(email: String, password: String): Single<User?> =
        userDao.isUser(email, password)

    override fun singUp(user: User): Single<Long> =
        userDao.insert(user)

    override fun singOut() {
        pref.clearUser()
    }
}
