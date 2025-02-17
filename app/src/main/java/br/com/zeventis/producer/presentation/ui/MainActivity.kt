package br.com.zeventis.producer.presentation.ui

import br.com.zeventis.producer.R
import br.com.zeventis.producer.core.plataform.BaseActivity
import br.com.zeventis.producer.presentation.ui.intro.IntroFragment

class MainActivity : BaseActivity() {

    override fun init() {
        setContentView(R.layout.activity_main)
        startFragment()
    }

    private fun startFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.mainActivityFl, IntroFragment.newInstance())
            .commit()
    }
}