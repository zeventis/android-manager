package br.com.zeventis.producer.presentation.ui.login

import android.content.Intent
import android.view.View
import br.com.zeventis.producer.R
import br.com.zeventis.producer.core.plataform.BaseFragment
import br.com.zeventis.producer.presentation.model.authentication.Login
import br.com.zeventis.producer.presentation.ui.home.HomeActivity
import kotlinx.android.synthetic.main.fragment_login.loginFragmentBackBt
import kotlinx.android.synthetic.main.fragment_login.loginFragmentLoginBtn
import kotlinx.android.synthetic.main.fragment_login.loginFragmentLoginLoadingPb
import kotlinx.android.synthetic.main.fragment_login.loginFragmentPasswordIl
import kotlinx.android.synthetic.main.fragment_login.loginFragmentUserIl
import org.koin.android.ext.android.inject

class LoginFragment : BaseFragment() {

    private val loginViewModel: LoginViewModel by inject()

    override fun getContentLayoutId(): Int = R.layout.fragment_login

    override fun init() {
        observeViewModelEvents()
        observeViewModelStates()
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
        }

        loginFragmentBackBt.setOnClickListener { activity?.onBackPressed() }
    }

    private fun observeViewModelEvents() {
        loginViewModel.viewEvent.observe(viewLifecycleOwner, {
            when (it) {
                is LoginViewEvents.OnLoginSuccess -> handleLoginSuccess()
                is LoginViewEvents.OnLoginFailed -> handleError(
                    this::class.java.toString(),
                    it.exceptionError
                )
            }
        })
    }

    private fun observeViewModelStates() {
        loginViewModel.viewState.observe(viewLifecycleOwner, {
            when (it) {
                is LoginViewState.ShowLoading -> showLoading()
                is LoginViewState.HideLoading -> hideLoading(it.success)
            }
        })
    }

    private fun hideLoading(success: Boolean) {
        if (!success) loginFragmentLoginBtn.visibility = View.VISIBLE
        loginFragmentLoginLoadingPb.visibility = View.GONE
    }

    private fun showLoading() {
        loginFragmentLoginLoadingPb.visibility = View.VISIBLE
        loginFragmentLoginBtn.visibility = View.GONE
    }

    private fun handleLoginSuccess() {
        startActivity(Intent(activity, HomeActivity::class.java))
        activity?.finish()
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}