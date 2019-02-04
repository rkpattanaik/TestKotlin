package com.rkpattanaik.testkotlin.signup

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.text.TextUtils
import android.util.Patterns

class SignupViewModel : ViewModel() {
    val validNameLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val validEmailLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val validAgeLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val launchDetailScreenLiveData: MutableLiveData<Boolean> = MutableLiveData()

    private fun isValidName(name: String) = !TextUtils.isEmpty(name)

    private fun isValidEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidAge(age: Int) = age in 20..51

    /**
     * This method is called when Sign Up button is clicked
     */
    fun onSignupBtnClicked(name: String, email: String, age: Int) {
        if (!isValidName(name)) {
            validNameLiveData.value = false
            return
        } else {
            validNameLiveData.value = true
        }

        if (!isValidEmail(email)) {
            validEmailLiveData.value = false
            return
        } else {
            validEmailLiveData.value = true
        }

        if (!isValidAge(age)) {
            validAgeLiveData.value = false
            return
        } else {
            validAgeLiveData.value = true
        }

        launchDetailScreenLiveData.value = true
    }
}