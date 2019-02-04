package com.rkpattanaik.testkotlin.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.rkpattanaik.testkotlin.R
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "EXTRA_NAME"
        const val EXTRA_EMAIL = "EXTRA_EMAIL"
        const val EXTRA_AGE = "EXTRA_AGE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        intent?.let {
            tvName.text = it.getStringExtra(EXTRA_NAME)
            tvEmail.text = it.getStringExtra(EXTRA_EMAIL)
            tvAge.text = it.getIntExtra(EXTRA_AGE, 0).toString()
        }
    }
}
