package br.com.zeventis.managerapp.presentation.ui.register

import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.core.plataform.BaseActivity

class RegisterActivity : BaseActivity() {

    override fun init() {
        setContentView(R.layout.activity_register)
        startFragment()
    }

    private fun startFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.registerActivityContainerFl, RegisterFragment.newInstance())
            .commit()
    }
}