package br.com.zeventis.managerapp.presentation.ui.intro

import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.core.plataform.BaseFragment
import br.com.zeventis.managerapp.presentation.ui.login.LoginFragment
import kotlinx.android.synthetic.main.fragment_intro.*

class IntroFragment : BaseFragment() {
    override fun getContentLayoutId(): Int = R.layout.fragment_intro

    override fun init() {
        initSetClickListeners()
    }

    private fun initSetClickListeners() {
        introFragmentLoginBt.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.mainActivityNavControllerFl,
                    LoginFragment.newInstance(),
                    LoginFragment.newInstance().javaClass.simpleName
                )?.commit()
        }
        introFragmentRegisterBt.setOnClickListener {
            // TODO Call Promoter login fragment
        }
    }

    override fun observeViewModelEvents() {
        // TODO Don't have implementation
    }

    companion object {
        fun newInstance() = IntroFragment()
    }
}