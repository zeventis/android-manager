package br.com.zeventis.producer.presentation.ui.home

import br.com.zeventis.producer.R
import br.com.zeventis.producer.core.plataform.BaseActivity

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