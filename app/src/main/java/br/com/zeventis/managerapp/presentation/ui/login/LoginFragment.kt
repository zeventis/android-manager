package br.com.zeventis.managerapp.presentation.ui.login

import android.content.Intent
import android.os.Handler
import android.view.View
import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.core.plataform.BaseFragment
import br.com.zeventis.managerapp.presentation.model.authentication.Login
import br.com.zeventis.managerapp.presentation.ui.home.HomeActivity
import kotlinx.android.synthetic.main.fragment_login.loginFragmentBackBt
import kotlinx.android.synthetic.main.fragment_login.loginFragmentLoadingLayout
import kotlinx.android.synthetic.main.fragment_login.loginFragmentLoginBtn
import kotlinx.android.synthetic.main.fragment_login.loginFragmentPasswordIl
import kotlinx.android.synthetic.main.fragment_login.loginFragmentUserIl
import kotlinx.android.synthetic.main.loading_layout.loadingLa
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
                    loginFragmentUserIl.editText?.text.toString(),
                    loginFragmentPasswordIl.editText?.text.toString()
                )
            )

            loginFragmentLoadingLayout.visibility = View.VISIBLE
            loadingLa.setMinAndMaxProgress(0f, 0.342f)
            loadingLa.playAnimation()
        }

        loginFragmentBackBt.setOnClickListener { activity?.onBackPressed() }
    }

    private fun observeViewModelEvents() {
        loginViewModel.viewState.observe(viewLifecycleOwner, {
            when (it) {
                is LoginViewEvents.OnLoginSuccess -> handleLoginSuccess()
                is LoginViewEvents.OnLoginFailed -> {
                    handleCustomError()
                    handleError(
                        LoginFragment::class.java.toString(),
                        it.exceptionError
                    )
                }
            }
        })
    }

    private fun handleCustomError() {
        loadingLa.setMinAndMaxProgress(0.797f, 0.941F)
        loadingLa.playAnimation()
        Handler().postDelayed({
            loginFragmentLoadingLayout.visibility = View.GONE
        }, 2400)
    }

    private fun handleLoginSuccess() {
        loadingLa.setMinAndMaxProgress(0.344f, 0.5F)
        loadingLa.playAnimation()

        Handler().postDelayed({
            loginFragmentLoadingLayout.visibility = View.GONE
            startActivity(Intent(activity, HomeActivity::class.java))
            activity?.finish()
        }, 2400)
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}