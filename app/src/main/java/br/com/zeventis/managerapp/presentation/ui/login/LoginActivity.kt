package br.com.zeventis.managerapp.presentation.ui.login

import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.core.plataform.BaseActivity

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