package br.com.zeventis.producer.presentation.ui.login

import br.com.zeventis.producer.R
import br.com.zeventis.producer.core.plataform.BaseActivity

class LoginActivity : BaseActivity() {

    override fun init() {
        setContentView(R.layout.activity_login)
        startFragment()
    }

    private fun startFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.loginActivityContainerFl, LoginFragment.newInstance())
            .commit()
    }
}