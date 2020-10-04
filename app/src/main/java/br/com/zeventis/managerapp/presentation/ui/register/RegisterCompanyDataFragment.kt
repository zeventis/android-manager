package br.com.zeventis.managerapp.presentation.ui.register

import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.core.plataform.BaseFragment
import kotlinx.android.synthetic.main.fragment_register_company_data.*
import kotlinx.android.synthetic.main.fragment_register_personal_data.*
import kotlinx.android.synthetic.main.fragment_register_personal_data.registerFragmentNext2Btn

class RegisterCompanyDataFragment : BaseFragment() {

    override fun getContentLayoutId(): Int = R.layout.fragment_register_company_data

    override fun init() {
        initOnClickListeners()
    }

    private fun initOnClickListeners() {
        registerFragmentNextBtn.setOnClickListener {
            // TODO Implements action change View Pager and change text
        }
    }

    companion object {
        fun newInstance() = RegisterCompanyDataFragment()
    }
}