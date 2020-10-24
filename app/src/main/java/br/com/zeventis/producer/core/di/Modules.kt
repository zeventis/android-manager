package br.com.zeventis.producer.core.di

import br.com.zeventis.producer.core.network.AuthInterceptor
import br.com.zeventis.producer.core.network.SessionManager
import br.com.zeventis.producer.core.network.provideApi
import br.com.zeventis.producer.core.network.provideRetrofit
import br.com.zeventis.producer.core.utils.RegisterManager
import br.com.zeventis.producer.data.repository.AddEventRepository
import br.com.zeventis.producer.data.repository.HomeRepository
import br.com.zeventis.producer.data.repository.LoginRepository
import br.com.zeventis.producer.data.repository.RegisterRepository
import br.com.zeventis.producer.domain.repository.IAddEventRepository
import br.com.zeventis.producer.domain.repository.IHomeRepository
import br.com.zeventis.producer.domain.repository.ILoginRepository
import br.com.zeventis.producer.domain.repository.IRegisterRepository
import br.com.zeventis.producer.domain.usecase.AddEventUseCase
import br.com.zeventis.producer.domain.usecase.GetCompanyUseCase
import br.com.zeventis.producer.domain.usecase.HomeUseCase
import br.com.zeventis.producer.domain.usecase.LoginUseCase
import br.com.zeventis.producer.domain.usecase.RegisterUseCase
import br.com.zeventis.producer.presentation.ui.addevent.AddEventViewModel
import br.com.zeventis.producer.presentation.ui.home.HomeViewModel
import br.com.zeventis.producer.presentation.ui.login.LoginViewModel
import br.com.zeventis.producer.presentation.ui.register.RegisterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

// TODO Config koinScope at all modules using initializer's
val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegisterViewModel(get(), get(), get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { AddEventViewModel(get()) }
}

val repositoryModule = module {
    single<ILoginRepository> { LoginRepository(get()) }
    single<IRegisterRepository> { RegisterRepository(get()) }
    single<IHomeRepository> { HomeRepository(get()) }
    single<IAddEventRepository> { AddEventRepository(get()) }
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
    factory { AddEventUseCase(get()) }
    factory { GetCompanyUseCase(get()) }
}