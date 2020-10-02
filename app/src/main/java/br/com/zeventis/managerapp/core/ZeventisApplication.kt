package br.com.zeventis.managerapp.core

import android.app.Application
import br.com.zeventis.managerapp.core.di.apiModule
import br.com.zeventis.managerapp.core.di.repositoryModule
import br.com.zeventis.managerapp.core.di.useCaseModule
import br.com.zeventis.managerapp.core.di.viewModelModule
import org.koin.android.ext.android.startKoin
import org.koin.standalone.KoinComponent

class ZeventisApplication : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        startKoin(
            this,
            listOf(
                apiModule,
                viewModelModule,
                repositoryModule,
                useCaseModule
            )
        )
    }
}