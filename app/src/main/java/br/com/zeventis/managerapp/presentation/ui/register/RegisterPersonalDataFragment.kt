package br.com.zeventis.managerapp.presentation.ui.register

import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.core.plataform.BaseFragment
import br.com.zeventis.managerapp.presentation.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_register_company_data.*
import kotlinx.android.synthetic.main.fragment_register_company_data.registerFragmentNextBtn
import kotlinx.android.synthetic.main.fragment_register_personal_data.*
import kotlinx.android.synthetic.main.fragment_register_user_data.*
import org.koin.android.ext.android.inject

class RegisterPersonalDataFragment : BaseFragment() {

    override fun getContentLayoutId(): Int = R.layout.fragment_register_personal_data

    override fun init() {
        initOnClickListeners()
    }

    private fun initOnClickListeners() {
        registerFragmentNext2Btn.setOnClickListener {
            // TODO Implements action change View Pager and change text
        }
    }

    companion object {
        fun newInstance() = RegisterPersonalDataFragment()
    }
}