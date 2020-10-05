package br.com.zeventis.managerapp.presentation.ui.register

import android.content.Intent
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.core.plataform.BaseFragment
import br.com.zeventis.managerapp.core.utils.RegisterManager
import br.com.zeventis.managerapp.presentation.ui.home.HomeActivity
import com.irozon.sneaker.Sneaker
import kotlinx.android.synthetic.main.activity_register.*
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

    private fun updateRegisterSingleton() {
        val registerTemp = registerManager.getRegister()
        registerTemp.username = registerFragmentUsernameEl.editText?.text.toString()
        registerTemp.email = registerFragmentEmailEl.editText?.text.toString()
        registerTemp.password = registerFragmentPasswordIl.editText?.text.toString()
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
                    RegisterUserDataFragment::class.java.toString(),
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
        registerManager.clearRegister()
        activity?.finish()
    }

    companion object {
        fun newInstance() = RegisterUserDataFragment()
    }
}