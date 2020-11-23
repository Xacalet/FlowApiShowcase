package com.xacalet.flowapishowcase

import android.app.Application
import com.xacalet.flowapishowcase.di.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.KoinExperimentalAPI
import org.koin.core.context.startKoin
import org.koin.core.logger.PrintLogger

class FlowApiShowcaseApplication: Application() {

    @KoinExperimentalAPI
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@FlowApiShowcaseApplication)
            fragmentFactory()
            logger(PrintLogger())
            modules(koinModule)
        }
    }
}
