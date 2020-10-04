package br.com.zeventis.managerapp.presentation.ui

import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.core.plataform.BaseActivity
import br.com.zeventis.managerapp.presentation.ui.intro.IntroFragment

class MainActivity : BaseActivity() {

    override fun init() {
        setContentView(R.layout.activity_main)
        startFragment()
    }

    private fun startFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.mainActivityNavControllerFl, IntroFragment.newInstance())
            .commit()
    }
}