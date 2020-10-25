package br.com.zeventis.producer.presentation.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zeventis.producer.core.network.SessionManager
import br.com.zeventis.producer.core.plataform.BaseViewModel
import br.com.zeventis.producer.domain.usecase.GetCompanyUseCase
import br.com.zeventis.producer.domain.usecase.RegisterUseCase
import br.com.zeventis.producer.presentation.mapper.login.UserMapper
import br.com.zeventis.producer.presentation.mapper.register.CompanySearchRegisterMapper
import br.com.zeventis.producer.presentation.mapper.register.RegisterMapper
import br.com.zeventis.producer.presentation.model.register.Register
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase,
    private val getCompanyUseCase: GetCompanyUseCase,
    private val sessionManager: SessionManager
) : BaseViewModel() {

    private var event = MutableLiveData<RegisterViewEvents>()
    val viewEvent: LiveData<RegisterViewEvents> = event

    private var state = MutableLiveData<RegisterViewState>()
    val viewState: LiveData<RegisterViewState> = state

    fun register(register: Register) {
        viewModelScope.launch {
            try {
                state.value = RegisterViewState.ShowLoading()

                val response = registerUseCase.execute(RegisterMapper.transformTo(register))
                val userAtPresentationModel = UserMapper.transformFrom(response)
                sessionManager.saveUser(userAtPresentationModel)

                event.value = RegisterViewEvents.OnRegisterSuccess(userAtPresentationModel)
                state.value = RegisterViewState.HideLoading(true)
            } catch (exception: Exception) {
                event.value = RegisterViewEvents.OnRegisterFailed(exception)
                state.value = RegisterViewState.HideLoading(false)
            }
        }
    }

    fun getCompany(companyName: String) {
        viewModelScope.launch {
            try {
                state.value = RegisterViewState.ShowCompanyLoading()

                val response = getCompanyUseCase.execute(companyName)
                if (response.isEmpty()) {
                    event.value = RegisterViewEvents.OnGetCompanyNotFound()
                } else {
                    event.value = RegisterViewEvents.OnGetCompanySuccess(
                        CompanySearchRegisterMapper.transformFromList(response)
                    )
                }

                state.value = RegisterViewState.HideCompanyLoading()
            } catch (exception: Exception) {
                event.value = RegisterViewEvents.OnGetCompanyFailed(exception)
                state.value = RegisterViewState.HideCompanyLoading()
            }

        }
    }
}