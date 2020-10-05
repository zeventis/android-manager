package br.com.zeventis.managerapp.presentation.ui.register

import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.core.plataform.BaseFragment
import br.com.zeventis.managerapp.core.utils.RegisterManager
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_register_personal_data.*
import org.koin.android.ext.android.inject

class RegisterPersonalDataFragment : BaseFragment() {

    private val registerManager: RegisterManager by inject()

    override fun getContentLayoutId(): Int = R.layout.fragment_register_personal_data

    override fun init() {
        initOnClickListeners()
    }

    private fun initOnClickListeners() {
        registerFragmentNext2Btn.setOnClickListener { handleNextButton() }
    }

    private fun handleNextButton() {
        updateRegisterSingleton()
        startNextRegisterFragment()
    }

    private fun startNextRegisterFragment() {
        val activity = activity as RegisterActivity
        activity.registerFragmentRegisterStepVp.currentItem = 1
    }

    private fun updateRegisterSingleton() {
        val registerTemp = registerManager.getRegister()
        registerTemp.name = registerFragmentNameEl.editText?.text.toString()
        registerTemp.phone = registerFragmentPhoneEl.editText?.text.toString()
        registerTemp.state = registerFragmentStateIl.editText?.text.toString()
        registerTemp.city = registerFragmentCityIl.editText?.text.toString()
        registerTemp.birthdayDate = registerFragmentBirthdayDateIl.editText?.text.toString()
        registerTemp.gender = registerFragmentGenderIl.editText?.text.toString()
        registerManager.saveRegister(registerTemp)
    }

    companion object {
        fun newInstance() = RegisterPersonalDataFragment()
    }
}