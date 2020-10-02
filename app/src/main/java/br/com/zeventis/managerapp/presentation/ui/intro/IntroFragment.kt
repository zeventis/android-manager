package br.com.zeventis.managerapp.presentation.ui.intro

import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.core.plataform.BaseFragment

class IntroFragment : BaseFragment() {
    override fun getContentLayoutId(): Int = R.layout.fragment_intro

    override fun init() {
    }

    override fun observeViewModelEvents() {
        // TODO Don't have implementation
    }

    companion object {
        fun newInstance() = IntroFragment()
    }
}