package com.xacalet.flowapishowcase.dialer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xacalet.flowapishowcase.R
import org.koin.androidx.fragment.android.setupKoinFragmentFactory
import org.koin.core.KoinExperimentalAPI

class DialerActivity : AppCompatActivity(R.layout.activity_dialer) {

    @KoinExperimentalAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        setupKoinFragmentFactory()
        super.onCreate(savedInstanceState)
    }
}
