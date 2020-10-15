package br.com.zeventis.managerapp.core.plataform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.zeventis.managerapp.R

abstract class BaseActivity : AppCompatActivity() {

    abstract fun init()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(
            R.anim.activity_slide_start_enter,
            R.anim.activity_scale_start_exit
        )
        init()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            R.anim.activity_scale_finish_enter,
            R.anim.activity_slide_finish_exit
        )
    }
}