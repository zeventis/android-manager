package br.com.zeventis.managerapp.presentation.ui.intro

import android.content.Intent
import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.core.plataform.BaseFragment
import br.com.zeventis.managerapp.presentation.ui.login.LoginActivity
import br.com.zeventis.managerapp.presentation.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.fragment_intro.introFragmentLoginBt
import kotlinx.android.synthetic.main.fragment_intro.introFragmentRegisterBt

class IntroFragment : BaseFragment() {
    override fun getContentLayoutId(): Int = R.layout.fragment_intro

    override fun init() {
        initSetClickListeners()
    }

    private fun initSetClickListeners() {
        introFragmentLoginBt.setOnClickListener {
            startActivity(Intent(activity, LoginActivity::class.java))
        }

        introFragmentRegisterBt.setOnClickListener {
            startActivity(Intent(activity, RegisterActivity::class.java))
        }
    }

    companion object {
        fun newInstance() = IntroFragment()
    }
}