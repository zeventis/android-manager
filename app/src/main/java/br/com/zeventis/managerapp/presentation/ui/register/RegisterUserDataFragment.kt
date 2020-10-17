package br.com.zeventis.managerapp.presentation.ui.register

import android.content.Intent
import android.view.View
import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.core.plataform.BaseFragment
import br.com.zeventis.managerapp.core.utils.RegisterManager
import br.com.zeventis.managerapp.domain.enum.ProfileTypeEnum
import br.com.zeventis.managerapp.presentation.ui.home.HomeActivity
import kotlinx.android.synthetic.main.fragment_register_user_data.registerFragmentDoneBtn
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
        initOnClickListeners()
    }

    override fun showLoading() {
        super.showLoading()
        registerFragmentDoneBtn.visibility = View.GONE
    }

    override fun hideLoading() {
        super.hideLoading()
        registerFragmentDoneBtn.visibility = View.VISIBLE
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
        registerViewModel.viewState.observe(viewLifecycleOwner, {
            when (it) {
                is RegisterViewEvents.OnRegisterSuccess -> handleRegisterSuccess()
                is RegisterViewEvents.OnRegisterFailed -> handleError(
                    this::class.java.toString(),
                    it.exceptionError
                )
            }
        })
    }

    private fun handleRegisterSuccess() {
        hideLoading()
        startActivity(Intent(activity, HomeActivity::class.java))
        registerManager.clearRegister()
        activity?.finish()
    }

    companion object {
        fun newInstance() = RegisterUserDataFragment()
    }
}