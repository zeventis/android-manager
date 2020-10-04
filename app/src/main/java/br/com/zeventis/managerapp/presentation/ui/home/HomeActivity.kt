package br.com.zeventis.managerapp.presentation.ui.home

import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.core.plataform.BaseActivity

class HomeActivity : BaseActivity() {

    override fun init() {
        setContentView(R.layout.activity_home)
        startFragment()
    }

    private fun startFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.homeActivityContainerFl, HomeFragment.newInstance())
            .commit()
    }
}