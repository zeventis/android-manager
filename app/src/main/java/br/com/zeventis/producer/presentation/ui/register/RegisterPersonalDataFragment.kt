package br.com.zeventis.producer.presentation.ui.register

import br.com.zeventis.producer.R
import br.com.zeventis.producer.core.components.MaskWatcher
import br.com.zeventis.producer.core.components.MaskWatcher.Companion.DATE_MASK
import br.com.zeventis.producer.core.components.MaskWatcher.Companion.PHONE_MASK
import br.com.zeventis.producer.core.plataform.BaseFragment
import br.com.zeventis.producer.core.utils.RegisterManager
import br.com.zeventis.producer.core.utils.extensions.formatDateToBackendFormat
import br.com.zeventis.producer.core.utils.extensions.unmask
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
        initMask()
    }

    private fun initMask() {
        registerFragmentPhoneIl.editText?.addTextChangedListener(MaskWatcher(PHONE_MASK))
        registerFragmentBirthdayDateIl.editText?.addTextChangedListener(MaskWatcher(DATE_MASK))
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
        registerTemp.phone = registerFragmentPhoneIl.editText?.text.toString().unmask()
        registerTemp.state = registerFragmentStateIl.editText?.text.toString()
        registerTemp.city = registerFragmentCityIl.editText?.text.toString()
        registerTemp.birthdayDate =
            registerFragmentBirthdayDateIl.editText?.text.toString().formatDateToBackendFormat()
        registerTemp.gender = registerFragmentGenderIl.editText?.text.toString()
        registerManager.saveRegister(registerTemp)
    }

    companion object {
        fun newInstance() = RegisterPersonalDataFragment()
    }
}