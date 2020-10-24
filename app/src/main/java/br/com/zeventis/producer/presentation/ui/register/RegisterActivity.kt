package br.com.zeventis.producer.presentation.ui.register

import android.os.Handler
import androidx.viewpager2.widget.ViewPager2
import br.com.zeventis.producer.R
import br.com.zeventis.producer.core.plataform.BaseActivity
import kotlinx.android.synthetic.main.activity_register.registerActivityRootNsv
import kotlinx.android.synthetic.main.activity_register.registerFragmentBackBt
import kotlinx.android.synthetic.main.activity_register.registerFragmentRegisterStepVp
import kotlinx.android.synthetic.main.activity_register.registerFragmentTitleTv

class RegisterActivity : BaseActivity() {

    override fun init() {
        setContentView(R.layout.activity_register)
        initOnClickListeners()
        initViewPagerAdapter()

        Handler().postDelayed({
            registerActivityRootNsv.scrollTo(
                registerFragmentBackBt.x.toInt(),
                registerFragmentBackBt.y.toInt()
            )
        }, 600)
    }

    private fun initViewPagerAdapter() {
        val registerStepsViewPagerAdapter =
            RegisterStepsViewPagerAdapter(supportFragmentManager, lifecycle)

        registerFragmentRegisterStepVp.adapter = registerStepsViewPagerAdapter

        registerFragmentRegisterStepVp.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                when (position) {
                    1 -> registerFragmentTitleTv.text = getString(R.string.register_company_infos)
                    2 -> registerFragmentTitleTv.text = getString(R.string.register_user_infos)
                    else -> registerFragmentTitleTv.text = getString(R.string.register_get_started)
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                registerActivityRootNsv.smoothScrollTo(
                    registerFragmentBackBt.x.toInt(),
                    registerFragmentBackBt.y.toInt()
                )
            }
        })
    }

    private fun initOnClickListeners() {
        registerFragmentBackBt.setOnClickListener { onBackPressed() }
    }
}