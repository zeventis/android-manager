package br.com.zeventis.producer.presentation.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zeventis.producer.core.network.SessionManager
import br.com.zeventis.producer.core.plataform.BaseViewModel
import br.com.zeventis.producer.domain.usecase.LoginUseCase
import br.com.zeventis.producer.presentation.mapper.login.LoginMapper
import br.com.zeventis.producer.presentation.mapper.login.UserMapper
import br.com.zeventis.producer.presentation.model.authentication.Login
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val sessionManager: SessionManager
) : BaseViewModel() {

    private var state = MutableLiveData<LoginViewEvents>()
    val viewState: LiveData<LoginViewEvents> = state

    fun doLogin(login: Login) {
        viewModelScope.launch {
            try {
                val response = loginUseCase.execute(LoginMapper.transformFrom(login))
                val userAtPresentationModel = UserMapper.transformFrom(response)
                sessionManager.saveUser(userAtPresentationModel)

                state.value = LoginViewEvents.OnLoginSuccess(userAtPresentationModel)
            } catch (exception: Exception) {
                state.value = LoginViewEvents.OnLoginFailed(exception)
            }
        }
    }
}