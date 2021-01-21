package br.com.zeventis.producer.presentation.ui.register

import android.text.InputType
import android.widget.EditText
import br.com.zeventis.producer.R
import br.com.zeventis.producer.core.plataform.BaseFragment
import br.com.zeventis.producer.core.utils.Constants
import br.com.zeventis.producer.core.utils.RegisterManager
import br.com.zeventis.producer.core.utils.extensions.bindView
import br.com.zeventis.producer.core.utils.extensions.formatDateToBackendFormat
import br.com.zeventis.producer.core.utils.extensions.unmask
import br.com.zeventis.producer.core.utils.validator.Validator
import br.com.zeventis.producer.core.utils.validator.validation.PhoneValidator
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

    private val validatorList: ArrayList<Validator> = arrayListOf()
    private val birthdayDateEditText: EditText by bindView(R.id.registerFragmentBirthdayDateEt)
    private val phoneEditText: EditText by bindView(R.id.registerFragmentPhoneEt)

    override fun getContentLayoutId(): Int = R.layout.fragment_register_personal_data

    override fun init() {
        initOnClickListeners()
        initMask()
        initValidator()
    }

    private fun initValidator() {
        validatorList.add(PhoneValidator(editText = phoneEditText, isRequiredField = true))
        validatorList.add(Validator(editText = phoneEditText, isRequiredField = true, classe = PhoneValidator::class.java))
    }

    private fun initMask() {
        maskField(birthdayDateEditText, Constants.Mask.DATE, Constants.MaskDigits.DATE, InputType.TYPE_CLASS_NUMBER)
        maskField(phoneEditText, Constants.Mask.PHONE, Constants.MaskDigits.PHONE, InputType.TYPE_CLASS_NUMBER)
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