package br.com.zeventis.managerapp.presentation.ui.home

import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.core.network.SessionManager
import br.com.zeventis.managerapp.core.plataform.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment() {

    private val sessionManager: SessionManager by inject()

    override fun getContentLayoutId(): Int = R.layout.fragment_home

    override fun init() {
        initUserLoggedText()
    }

    private fun initUserLoggedText() {
        homeFragmentUserTv.text =
            getString(R.string.home_user_logged, sessionManager.getTwoFirstNameUser())
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}