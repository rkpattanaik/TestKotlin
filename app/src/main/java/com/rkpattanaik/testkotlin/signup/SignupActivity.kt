package com.rkpattanaik.testkotlin.signup

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.rkpattanaik.testkotlin.detail.DetailsActivity
import com.rkpattanaik.testkotlin.R
import kotlinx.android.synthetic.main.activity_signup.*
import java.lang.NumberFormatException

class SignupActivity : AppCompatActivity() {

    private lateinit var viewModel: SignupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        viewModel = ViewModelProviders.of(this@SignupActivity).get(SignupViewModel::class.java)
        observeViewModel()

        btnSignUp.setOnClickListener {
            viewModel.onSignupBtnClicked(
                tipName.editText?.text.toString(),
                tipEmail.editText?.text.toString(),
                try {
                    tipAge.editText?.text.toString().toInt()
                } catch (e: NumberFormatException) {
                    0 // If user doesn't enter any age value set 0 to the age argument
                }
            )
        }
    }

    private fun observeViewModel() {
        viewModel.validNameLiveData.observe(this, Observer {
            tipName.error = if (it == false) getString(R.string.error_invalid_name) else null
        })

        viewModel.validEmailLiveData.observe(this, Observer {
            tipEmail.error = if (it == false) getString(R.string.error_invalid_email) else null
        })

        viewModel.validAgeLiveData.observe(this, Observer {
            tipAge.error = if (it == false) getString(R.string.error_invalid_age) else null
        })

        viewModel.launchDetailScreenLiveData.observe(this, Observer {
            if (it == true) {
                launchDetailsScreen()
                // After launching next screen, immediately set the livedata value to false
                viewModel.launchDetailScreenLiveData.value = false
            }
        })
    }

    private fun launchDetailsScreen() {
        val intent = Intent(this@SignupActivity, DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.EXTRA_NAME, tipName.editText?.text.toString())
        intent.putExtra(DetailsActivity.EXTRA_EMAIL, tipEmail.editText?.text.toString())
        intent.putExtra(DetailsActivity.EXTRA_AGE, tipAge.editText?.text.toString().toInt())
        startActivity(intent)
        finish()
    }
}
