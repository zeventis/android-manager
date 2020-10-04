package br.com.zeventis.managerapp.presentation.ui.register

import androidx.viewpager2.widget.ViewPager2
import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.core.plataform.BaseFragment
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment : BaseFragment() {

    override fun getContentLayoutId(): Int = R.layout.fragment_register

    override fun init() {
        initOnClickListeners()
        initViewPagerAdapter()
    }

    private fun initViewPagerAdapter() {
        val registerStepsViewPagerAdapter =
            RegisterStepsViewPagerAdapter(childFragmentManager, lifecycle)

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
        })
    }

    private fun initOnClickListeners() {
        registerFragmentBackBt.setOnClickListener { activity?.onBackPressed() }
    }

    companion object {
        fun newInstance() = RegisterFragment()
    }
}