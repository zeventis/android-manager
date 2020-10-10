package br.com.zeventis.managerapp.presentation.ui.register

import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.core.plataform.BaseFragment
import br.com.zeventis.managerapp.core.utils.RegisterManager
import br.com.zeventis.managerapp.presentation.model.register.Company
import kotlinx.android.synthetic.main.activity_register.registerFragmentRegisterStepVp
import kotlinx.android.synthetic.main.fragment_register_company_data.registerFragmentAddressComplementIl
import kotlinx.android.synthetic.main.fragment_register_company_data.registerFragmentAddressNumberIl
import kotlinx.android.synthetic.main.fragment_register_company_data.registerFragmentCepIl
import kotlinx.android.synthetic.main.fragment_register_company_data.registerFragmentCompanyNameEl
import kotlinx.android.synthetic.main.fragment_register_company_data.registerFragmentNextBtn
import kotlinx.android.synthetic.main.fragment_register_company_data.registerFragmentPhoneCompanyEl
import org.koin.android.ext.android.inject

class RegisterCompanyDataFragment : BaseFragment() {

    private val registerManager: RegisterManager by inject()

    override fun getContentLayoutId(): Int = R.layout.fragment_register_company_data

    override fun init() {
        initOnClickListeners()
    }

    private fun initOnClickListeners() {
        registerFragmentNextBtn.setOnClickListener { handleNextButton() }
    }

    private fun handleNextButton() {
        startNextRegisterFragment()
        updateRegisterSingleton()
    }

    private fun startNextRegisterFragment() {
        val activity = activity as RegisterActivity
        activity.registerFragmentRegisterStepVp.currentItem = 2
    }

    private fun updateRegisterSingleton() {
        val registerTemp = registerManager.getRegister()
        val companyTemp = Company()
        companyTemp.name = registerFragmentCompanyNameEl.editText?.text.toString()
        companyTemp.phone = registerFragmentPhoneCompanyEl.editText?.text.toString()
        companyTemp.cep = registerFragmentCepIl.editText?.text.toString()
        companyTemp.addressNumber = registerFragmentAddressNumberIl.editText?.text.toString()
        companyTemp.addressComplement =
            registerFragmentAddressComplementIl.editText?.text.toString()
        registerTemp.company = companyTemp
        registerManager.saveRegister(registerTemp)
    }

    companion object {
        fun newInstance() = RegisterCompanyDataFragment()
    }
}