package br.com.zeventis.managerapp.presentation.ui.login

import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.core.plataform.BaseFragment
import br.com.zeventis.managerapp.core.network.SessionManager
import br.com.zeventis.managerapp.presentation.model.Login
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject

class LoginFragment : BaseFragment() {

    private val loginViewModel: LoginViewModel by inject()
    private val sessionManager: SessionManager by inject()

    override fun getContentLayoutId(): Int = R.layout.fragment_login

    override fun init() {
        loginFragmentLoginBtn.setOnClickListener {
            loginViewModel.doLogin(
                Login(
                    loginFragmentUserEt.text.toString(),
                    loginFragmentPasswordIl.editText?.text.toString()
                )
            )
        }
    }

    override fun observeViewModelEvents() {
        loginViewModel.viewState.observe(viewLifecycleOwner, {
            when (it) {
                is LoginViewEvents.OnLoginSuccess -> handleLoginSuccess()
                is LoginViewEvents.OnLoginFailed -> handleError(
                    LoginFragment::class.java.toString(),
                    it.exceptionError
                )
            }
        })
    }

    private fun handleLoginSuccess() {
        val user = sessionManager.getUser()
        loginFragmentLoginDataTv.text = user.toString()
        //TODO Do login action after backend service implementation
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}