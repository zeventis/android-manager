package br.com.zeventis.managerapp.presentation.ui

import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.core.plataform.BaseActivity
import br.com.zeventis.managerapp.presentation.ui.login.LoginFragment

class MainActivity : BaseActivity() {
    override fun init() {
        setContentView(R.layout.activity_main)
        startFragment()
    }

    private fun startFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.mainActivityContainerFl,
                LoginFragment.newInstance(),
                LoginFragment.newInstance().javaClass.simpleName
            )
            .commit()
    }
}