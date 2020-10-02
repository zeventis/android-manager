package br.com.zeventis.managerapp.presentation.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.zeventis.managerapp.core.plataform.BaseViewModel
import br.com.zeventis.managerapp.domain.usecase.LoginUseCase
import br.com.zeventis.managerapp.presentation.mapper.login.LoginMapper
import br.com.zeventis.managerapp.presentation.mapper.login.UserMapper
import br.com.zeventis.managerapp.presentation.model.Login
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase) : BaseViewModel() {

    private var state = MutableLiveData<LoginViewEvents>()
    val viewState: LiveData<LoginViewEvents> = state

    fun doLogin(login: Login) {
        launch {
            try {
                val response = loginUseCase.execute(LoginMapper.transformFrom(login))
                state.value =
                    LoginViewEvents.OnLoginSuccess(UserMapper.transformFrom(response))
            } catch (exception: Exception) {
                state.value = LoginViewEvents.OnLoginFailed(exception)
            }
        }
    }
}