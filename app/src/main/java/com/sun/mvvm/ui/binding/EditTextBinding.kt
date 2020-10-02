package com.sun.mvvm.ui.binding

import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("errorText")
fun TextInputLayout.bindError(@StringRes resId: Int?) {
    error = resId?.let { context.getString(resId) } ?: ""
}
