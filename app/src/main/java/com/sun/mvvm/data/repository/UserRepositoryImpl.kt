package com.sun.mvvm.data.repository

import com.sun.mvvm.data.model.User
import com.sun.mvvm.data.source.UserDataSource
import io.reactivex.Single

class UserRepositoryImpl(
    private val local: UserDataSource.Local
) : UserRepository {

    override fun getCurrentUser(): Single<User> =
        local.getCurrentUser()

    override fun signIn(email: String, password: String): Single<User?> =
        local.singIn(email, password)

    override fun signUp(user: User): Single<Long> =
        local.singUp(user)

    override fun signOut() {
        local.singOut()
    }

    override fun setCurrentUser(user: User) {
        local.setCurrentUser(user)
    }
}
