package br.com.zeventis.managerapp.presentation.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.zeventis.managerapp.core.network.SessionManager
import br.com.zeventis.managerapp.core.plataform.BaseViewModel
import br.com.zeventis.managerapp.domain.usecase.GetCompanyUseCase
import br.com.zeventis.managerapp.domain.usecase.RegisterUseCase
import br.com.zeventis.managerapp.presentation.mapper.login.UserMapper
import br.com.zeventis.managerapp.presentation.mapper.register.CompanySearchRegisterMapper
import br.com.zeventis.managerapp.presentation.mapper.register.RegisterMapper
import br.com.zeventis.managerapp.presentation.model.register.Register
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase,
    private val getCompanyUseCase: GetCompanyUseCase,
    private val sessionManager: SessionManager
) : BaseViewModel() {

    private var state = MutableLiveData<RegisterViewEvents>()
    val viewState: LiveData<RegisterViewEvents> = state

    fun register(register: Register) {
        launch {
            try {
                val response = registerUseCase.execute(RegisterMapper.transformTo(register))
                val userAtPresentationModel = UserMapper.transformFrom(response)
                sessionManager.saveUser(userAtPresentationModel)

                state.value = RegisterViewEvents.OnRegisterSuccess(userAtPresentationModel)
            } catch (exception: Exception) {
                state.value = RegisterViewEvents.OnRegisterFailed(exception)
            }
        }
    }

    fun getCompany(companyName: String) {
        launch {
            try {
                val response = getCompanyUseCase.execute(companyName)

                if (response.isEmpty()) {
                    state.value = RegisterViewEvents.OnGetCompanyNotFound()
                } else {
                    state.value = RegisterViewEvents.OnGetCompanySuccess(
                        CompanySearchRegisterMapper.transformFromList(response)
                    )
                }
            } catch (exception: Exception) {
                state.value = RegisterViewEvents.OnGetCompanyFailed(exception)
            }

        }
    }
}