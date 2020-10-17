package br.com.zeventis.managerapp.presentation.ui.register

import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.core.plataform.BaseFragment
import br.com.zeventis.managerapp.core.utils.RegisterManager
import kotlinx.android.synthetic.main.activity_register.registerFragmentRegisterStepVp
import kotlinx.android.synthetic.main.fragment_register_personal_data.registerFragmentBirthdayDateIl
import kotlinx.android.synthetic.main.fragment_register_personal_data.registerFragmentCityIl
import kotlinx.android.synthetic.main.fragment_register_personal_data.registerFragmentGenderIl
import kotlinx.android.synthetic.main.fragment_register_personal_data.registerFragmentNameIl
import kotlinx.android.synthetic.main.fragment_register_personal_data.registerFragmentNext2Btn
import kotlinx.android.synthetic.main.fragment_register_personal_data.registerFragmentPhoneIl
import kotlinx.android.synthetic.main.fragment_register_personal_data.registerFragmentStateIl
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
        registerTemp.name = registerFragmentNameIl.editText?.text.toString()
        registerTemp.phone = registerFragmentPhoneIl.editText?.text.toString()
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