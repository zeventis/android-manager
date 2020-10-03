package br.com.zeventis.managerapp.core.di

import br.com.zeventis.managerapp.core.network.AuthInterceptor
import br.com.zeventis.managerapp.core.network.SessionManager
import br.com.zeventis.managerapp.core.network.provideApi
import br.com.zeventis.managerapp.core.network.provideRetrofit
import br.com.zeventis.managerapp.data.repository.LoginRepository
import br.com.zeventis.managerapp.domain.repository.ILoginRepository
import br.com.zeventis.managerapp.domain.usecase.LoginUseCase
import br.com.zeventis.managerapp.presentation.ui.login.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


// TODO Config koinScope at all modules using initializer's
val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
}

val repositoryModule = module {
    single<ILoginRepository> { LoginRepository(get()) }
}

val apiModule = module {
    factory { provideApi(get()) }
    single { provideRetrofit(get()) }
    single { SessionManager(androidContext()) }
    single { AuthInterceptor(androidContext()) }
}

val useCaseModule = module {
    factory { LoginUseCase(get()) }
}