package com.sun.mvvm.data.repository

import com.sun.mvvm.data.model.User
import io.reactivex.Single

interface UserRepository {

    fun getCurrentUser(): Single<User>

    fun signIn(email: String, password: String): Single<User?>

    fun signUp(user: User): Single<Long>

    fun signOut()

    fun setCurrentUser(user: User)
}
