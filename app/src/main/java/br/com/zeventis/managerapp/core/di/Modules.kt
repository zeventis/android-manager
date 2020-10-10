package br.com.zeventis.managerapp.core.di

import br.com.zeventis.managerapp.core.network.AuthInterceptor
import br.com.zeventis.managerapp.core.network.SessionManager
import br.com.zeventis.managerapp.core.network.provideApi
import br.com.zeventis.managerapp.core.network.provideRetrofit
import br.com.zeventis.managerapp.core.utils.RegisterManager
import br.com.zeventis.managerapp.data.repository.HomeRepository
import br.com.zeventis.managerapp.data.repository.LoginRepository
import br.com.zeventis.managerapp.data.repository.RegisterRepository
import br.com.zeventis.managerapp.domain.repository.IHomeRepository
import br.com.zeventis.managerapp.domain.repository.ILoginRepository
import br.com.zeventis.managerapp.domain.repository.IRegisterRepository
import br.com.zeventis.managerapp.domain.usecase.HomeUseCase
import br.com.zeventis.managerapp.domain.usecase.LoginUseCase
import br.com.zeventis.managerapp.domain.usecase.RegisterUseCase
import br.com.zeventis.managerapp.presentation.ui.home.HomeViewModel
import br.com.zeventis.managerapp.presentation.ui.login.LoginViewModel
import br.com.zeventis.managerapp.presentation.ui.register.RegisterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


// TODO Config koinScope at all modules using initializer's
val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegisterViewModel(get(), get()) }
    viewModel { HomeViewModel(get()) }
}

val repositoryModule = module {
    single<ILoginRepository> { LoginRepository(get()) }
    single<IRegisterRepository> { RegisterRepository(get()) }
    single<IHomeRepository> { HomeRepository(get()) }
}

val apiModule = module {
    factory { provideApi(get()) }
    single { provideRetrofit(get()) }
    single { AuthInterceptor(androidContext()) }
}

val managersModule = module {
    single { SessionManager(androidContext()) }
    single { RegisterManager(androidContext()) }
}

val useCaseModule = module {
    factory { LoginUseCase(get()) }
    factory { RegisterUseCase(get()) }
    factory { HomeUseCase(get()) }
}