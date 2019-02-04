package com.rkpattanaik.testkotlin

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.rkpattanaik.testkotlin.signup.SignupViewModel

class SignupViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SignupViewModel

    @Before
    fun setup() {
        viewModel = SignupViewModel()
    }

    @Test
    fun emptyNameInput_SetsNameLiveDataValueToFalse() {
        val nameObserver = mock<Observer<Boolean>>()
        viewModel.validNameLiveData.observeForever(nameObserver)
        viewModel.onSignupBtnClicked("", "", 0)
        verify(nameObserver).onChanged(false)
    }

    @Test
    fun validNameInput_SetsNameLiveDataValueToTrue() {
        val nameObserver = mock<Observer<Boolean>>()
        viewModel.validNameLiveData.observeForever(nameObserver)
        viewModel.onSignupBtnClicked("Rajesh", "", 0)
        verify(nameObserver).onChanged(true)
    }

    @Test
    fun invalidEmailInput_SetsEmailLiveDataValueToFalse() {
        val emailObserver = mock<Observer<Boolean>>()
        viewModel.validEmailLiveData.observeForever(emailObserver)
        viewModel.onSignupBtnClicked("Rajesh", "aaa", 0)
        verify(emailObserver).onChanged(false)
    }

    @Test
    fun validEmailInput_SetsEmailLiveDataValueToTrue() {
        val emailObserver = mock<Observer<Boolean>>()
        viewModel.validEmailLiveData.observeForever(emailObserver)
        viewModel.onSignupBtnClicked("Rajesh", "a@b.c", 0)
        verify(emailObserver).onChanged(true)
    }

    @Test
    fun ageNotBetween20To51_setsAgeLiveDataValueToFalse() {
        val ageObserver = mock<Observer<Boolean>>()
        viewModel.validAgeLiveData.observeForever(ageObserver)
        viewModel.onSignupBtnClicked("Rajesh", "a@b.c", 10)
        verify(ageObserver).onChanged(false)
    }

    @Test
    fun ageBetween20To51_setsAgeLiveDataValueToTrue() {
        val ageObserver = mock<Observer<Boolean>>()
        viewModel.validAgeLiveData.observeForever(ageObserver)
        viewModel.onSignupBtnClicked("Rajesh", "a@b.c", 20)
        verify(ageObserver).onChanged(true)
    }

    @Test
    fun anyInvalidInput_DoesNotSetLaunchDetailScreenLiveDataValueToTrue() {
        val launchDetailScreenObserver = mock<Observer<Boolean>>()
        viewModel.launchDetailScreenLiveData.observeForever(launchDetailScreenObserver)
        viewModel.onSignupBtnClicked("", "", 0)
        assert(viewModel.launchDetailScreenLiveData.value == false)
    }

    @Test
    fun allValidInputs_SetLaunchDetailScreenLiveDataValueToTrue() {
        val launchDetailScreenObserver = mock<Observer<Boolean>>()
        viewModel.launchDetailScreenLiveData.observeForever(launchDetailScreenObserver)
        viewModel.onSignupBtnClicked("Rajesh", "a@b.c", 29)
        verify(launchDetailScreenObserver).onChanged(true)
    }
}