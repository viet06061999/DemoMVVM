package com.sun.mvvm.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.mvvm.base.RxViewModel
import com.sun.mvvm.R
import com.sun.mvvm.utils.isEmail
import com.sun.mvvm.utils.isValidPassword
import com.sun.mvvm.data.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val userRepository: UserRepository) : RxViewModel() {

    val email = MutableLiveData<String>()
    val emailRule = MutableLiveData<Int>()
    val password = MutableLiveData<String>()
    val passwordRule = MutableLiveData<Int>()

    private val _isUser = MutableLiveData<Boolean>()
    val isUser: LiveData<Boolean>
        get() = _isUser

    fun signIn() {
        val emailVale = email.value
        val passwordValue = password.value

        emailRule.value =
            emailVale?.let {
                if (it.isEmail) null else R.string.msg_email_invalid
            }
        passwordRule.value =
            passwordValue?.let { if (it.isValidPassword) null else R.string.msg_weak_password }
        addToDisposable(
            userRepository.signIn(emailVale.toString(), passwordValue.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _isUser.value = it != null
                        it?.let { userRepository.setCurrentUser(it) }
                    },
                    {
                        _isUser.value = false
                    }
                ))
    }
}

