package br.com.zeventis.producer.presentation.ui.intro

import android.content.Intent
import br.com.zeventis.producer.R
import br.com.zeventis.producer.core.plataform.BaseFragment
import br.com.zeventis.producer.presentation.ui.login.LoginActivity
import br.com.zeventis.producer.presentation.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.fragment_intro.introFragmentLoginBt
import kotlinx.android.synthetic.main.fragment_intro.introFragmentRegisterBt

class IntroFragment : BaseFragment() {
    override fun getContentLayoutId(): Int = R.layout.fragment_intro

    override fun init() {
        initOnClickListeners()
    }

    private fun initOnClickListeners() {
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