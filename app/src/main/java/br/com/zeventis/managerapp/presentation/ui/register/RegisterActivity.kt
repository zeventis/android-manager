package br.com.zeventis.managerapp.presentation.ui.register

import android.os.Handler
import androidx.viewpager2.widget.ViewPager2
import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.core.plataform.BaseActivity
import kotlinx.android.synthetic.main.activity_register.*

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