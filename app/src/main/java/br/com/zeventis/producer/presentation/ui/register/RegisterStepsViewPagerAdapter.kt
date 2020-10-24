package br.com.zeventis.producer.presentation.ui.register

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class RegisterStepsViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> RegisterCompanyDataFragment.newInstance()
            2 -> RegisterUserDataFragment.newInstance()
            else -> RegisterPersonalDataFragment.newInstance()
        }
    }

}