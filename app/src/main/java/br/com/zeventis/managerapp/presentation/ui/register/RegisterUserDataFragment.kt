package br.com.zeventis.managerapp.presentation.ui.register

import android.content.Intent
import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.core.plataform.BaseFragment
import br.com.zeventis.managerapp.core.utils.RegisterManager
import br.com.zeventis.managerapp.domain.exception.SessionExpiredException
import br.com.zeventis.managerapp.presentation.ui.home.HomeActivity
import com.irozon.sneaker.Sneaker
import kotlinx.android.synthetic.main.fragment_register_user_data.*
import org.koin.android.ext.android.inject

class RegisterUserDataFragment : BaseFragment() {

    private val registerViewModel: RegisterViewModel by inject()
    private val registerManager: RegisterManager by inject()

    override fun getContentLayoutId(): Int = R.layout.fragment_register_user_data

    override fun init() {
        observeViewModelEvents()
        initOnClickListeners()
    }

    private fun initOnClickListeners() {
        registerFragmentDoneBtn.setOnClickListener {
            registerManager.getRegister()?.let { register ->
                registerViewModel.register(register)
            }
        }
    }

    private fun observeViewModelEvents() {
        registerViewModel.viewState.observe(viewLifecycleOwner, {
            when (it) {
                is RegisterViewEvents.OnRegisterSuccess -> handleRegisterSuccess()
                is RegisterViewEvents.OnRegisterFailed -> handleError(
                    RegisterFragment::class.java.toString(),
                    it.exceptionError
                )
            }
        })
    }

    private fun handleRegisterSuccess() {
        Sneaker.with(requireActivity())
            .setTitle("Cadastro")
            .setMessage("Você foi cadastrado com sucesso")
            .setCornerRadius(1)
            .setDuration(3000)
            .autoHide(true)
            .sneakSuccess()
        startActivity(Intent(activity, HomeActivity::class.java))
        activity?.finish()
    }


    companion object {
        fun newInstance() = RegisterUserDataFragment()
    }
}