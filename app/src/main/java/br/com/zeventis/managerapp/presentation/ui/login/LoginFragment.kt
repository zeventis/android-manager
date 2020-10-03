package br.com.zeventis.managerapp.presentation.ui.login

import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.core.plataform.BaseFragment
import br.com.zeventis.managerapp.presentation.model.Login
import br.com.zeventis.managerapp.presentation.model.User
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject

class LoginFragment : BaseFragment() {

    private val loginViewModel: LoginViewModel by inject()

    override fun getContentLayoutId(): Int = R.layout.fragment_login

    override fun init() {
        loginFragmentLoginBtn.setOnClickListener {
            loginViewModel.doLogin(
                Login(
                    "teste",
                    "testeSenha"
                )
            )
        } // TODO User edittext fields
    }

    override fun observeViewModelEvents() {
        loginViewModel.viewState.observe(viewLifecycleOwner, {
            when (it) {
                is LoginViewEvents.OnLoginSuccess -> handleLoginSuccess(it.user)
                is LoginViewEvents.OnLoginFailed -> handleError(
                    LoginFragment::class.java.toString(),
                    it.exceptionError
                )
            }
        })
    }

    private fun handleLoginSuccess(user: User) {
        loginFragmentLoginDataTv.text = user.toString()
        //TODO Do login action after backend service implementation
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}