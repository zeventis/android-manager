package br.com.zeventis.producer.presentation.ui.register

import android.content.Intent
import android.view.View
import br.com.zeventis.producer.R
import br.com.zeventis.producer.core.plataform.BaseFragment
import br.com.zeventis.producer.core.utils.RegisterManager
import br.com.zeventis.producer.domain.enum.ProfileTypeEnum
import br.com.zeventis.producer.presentation.ui.home.HomeActivity
import kotlinx.android.synthetic.main.fragment_register_user_data.registerFragmentDoneBtn
import kotlinx.android.synthetic.main.fragment_register_user_data.registerFragmentDoneLoadingPb
import kotlinx.android.synthetic.main.fragment_register_user_data.registerFragmentEmailIl
import kotlinx.android.synthetic.main.fragment_register_user_data.registerFragmentInstagramIl
import kotlinx.android.synthetic.main.fragment_register_user_data.registerFragmentPasswordIl
import kotlinx.android.synthetic.main.fragment_register_user_data.registerFragmentUsernameIl
import org.koin.android.ext.android.inject


class RegisterUserDataFragment : BaseFragment() {

    private val registerViewModel: RegisterViewModel by inject()
    private val registerManager: RegisterManager by inject()

    override fun getContentLayoutId(): Int = R.layout.fragment_register_user_data

    override fun init() {
        observeViewModelEvents()
        observeViewModelStates()
        initOnClickListeners()
    }

    private fun updateRegisterSingleton() {
        val registerTemp = registerManager.getRegister()
        registerTemp.username = registerFragmentUsernameIl.editText?.text.toString()
        registerTemp.email = registerFragmentEmailIl.editText?.text.toString()
        registerTemp.password = registerFragmentPasswordIl.editText?.text.toString()
        registerTemp.instagram = registerFragmentInstagramIl.editText?.text.toString()
        registerTemp.profile = ProfileTypeEnum.FREE
        registerManager.saveRegister(registerTemp)
    }

    private fun initOnClickListeners() {
        registerFragmentDoneBtn.setOnClickListener {
            updateRegisterSingleton()
            registerViewModel.register(registerManager.getRegister())
        }
    }

    private fun observeViewModelEvents() {
        registerViewModel.viewEvent.observe(viewLifecycleOwner, {
            when (it) {
                is RegisterViewEvents.OnRegisterSuccess -> handleRegisterSuccess()
                is RegisterViewEvents.OnRegisterFailed -> handleError(
                    this::class.java.toString(),
                    it.exceptionError
                )
            }
        })
    }

    private fun observeViewModelStates() {
        registerViewModel.viewState.observe(viewLifecycleOwner, {
            when (it) {
                is RegisterViewState.ShowLoading -> showLoading()
                is RegisterViewState.HideLoading -> hideLoading(it.success)
            }
        })
    }

    private fun hideLoading(success: Boolean) {
        if (!success) registerFragmentDoneBtn.visibility = View.VISIBLE
        registerFragmentDoneLoadingPb.visibility = View.GONE
    }

    private fun showLoading() {
        registerFragmentDoneLoadingPb.visibility = View.VISIBLE
        registerFragmentDoneBtn.visibility = View.GONE
    }

    private fun handleRegisterSuccess() {
        startActivity(Intent(activity, HomeActivity::class.java))
        registerManager.clearRegister()
        activity?.finish()
    }

    companion object {
        fun newInstance() = RegisterUserDataFragment()
    }
}