package br.com.zeventis.managerapp.presentation.ui.login

import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.core.plataform.BaseFragment
import br.com.zeventis.managerapp.presentation.model.Login
import br.com.zeventis.managerapp.presentation.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject

class LoginFragment : BaseFragment() {

    private val loginViewModel: LoginViewModel by inject()

    override fun getContentLayoutId(): Int = R.layout.fragment_login

    override fun init() {
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
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(
                R.id.mainActivityNavControllerFl,
                HomeFragment.newInstance(),
                HomeFragment.newInstance().javaClass.simpleName
            )?.commit()
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}