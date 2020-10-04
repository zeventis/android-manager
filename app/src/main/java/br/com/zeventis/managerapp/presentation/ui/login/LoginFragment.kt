package br.com.zeventis.managerapp.presentation.ui.login

import android.content.Intent
import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.core.plataform.BaseFragment
import br.com.zeventis.managerapp.presentation.model.login.Login
import br.com.zeventis.managerapp.presentation.ui.home.HomeActivity
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject

class LoginFragment : BaseFragment() {

    private val loginViewModel: LoginViewModel by inject()

    override fun getContentLayoutId(): Int = R.layout.fragment_login

    override fun init() {
        observeViewModelEvents()
        initOnClickListeners()
    }

    private fun initOnClickListeners() {
        loginFragmentLoginBtn.setOnClickListener {
            loginViewModel.doLogin(
                Login(
                    loginFragmentUserEl.editText?.text.toString(),
                    loginFragmentPasswordIl.editText?.text.toString()
                )
            )
        }

        loginFragmentBackBt.setOnClickListener { activity?.onBackPressed() }
    }

    private fun observeViewModelEvents() {
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
        startActivity(Intent(activity, HomeActivity::class.java))
        activity?.finish()
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}