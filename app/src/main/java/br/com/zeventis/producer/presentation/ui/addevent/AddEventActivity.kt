package br.com.zeventis.producer.presentation.ui.addevent

import br.com.zeventis.producer.R
import br.com.zeventis.producer.core.plataform.BaseActivity

class AddEventActivity : BaseActivity() {

    override fun init() {
        setContentView(R.layout.activity_add_event)
        startFragment()
    }

    private fun startFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.addEventActivityContainerFl, AddEventFragment.newInstance())
            .commit()
    }
}