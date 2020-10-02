package com.sun.mvvm.data.source

import com.sun.mvvm.data.model.User
import io.reactivex.Single

interface UserDataSource {
    interface Local {
        fun setCurrentUser(user: User)

        fun getCurrentUser(): Single<User>

        fun singIn(email: String, password: String): Single<User?>

        fun singUp(user: User): Single<Long>

        fun singOut()
    }
}
