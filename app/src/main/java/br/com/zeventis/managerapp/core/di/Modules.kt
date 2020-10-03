package br.com.zeventis.managerapp.core.di

import br.com.zeventis.managerapp.core.api.provideApi
import br.com.zeventis.managerapp.core.api.provideRetrofit
import br.com.zeventis.managerapp.data.repository.LoginRepository
import br.com.zeventis.managerapp.domain.repository.ILoginRepository
import br.com.zeventis.managerapp.domain.usecase.LoginUseCase
import br.com.zeventis.managerapp.presentation.ui.login.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


// TODO Config koinScope at all modules using initializer's
val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
}

val repositoryModule = module {
    single<ILoginRepository> { LoginRepository(androidContext(), get()) }
}

val apiModule = module {
    factory { provideApi(get()) }
    single { provideRetrofit() }
}

val useCaseModule = module {
    factory { LoginUseCase(get()) }
}