package br.com.zeventis.managerapp.core.plataform

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.domain.exception.SessionExpiredException
import com.irozon.sneaker.Sneaker
import java.net.UnknownHostException

abstract class BaseFragment : Fragment() {

    protected abstract fun getContentLayoutId(): Int
    protected abstract fun init()
    protected abstract fun observeViewModelEvents()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelEvents()
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getContentLayoutId(), container, false)
    }

    protected fun handleError(tag: String, error: Throwable) {
        //TODO Handle all errors exception
        if (activity != null && error == SessionExpiredException()) { // TODO Redirect user to login screen
            Sneaker.with(requireActivity())
                .setTitle("Erro")
                .setMessage(SessionExpiredException().message.toString())
                .setCornerRadius(1)
                .setDuration(3000)
                .autoHide(true)
                .sneakError()
        }

        when (error) {
            is UnknownHostException -> getString(R.string.generic_server_down)
            is SessionExpiredException -> SessionExpiredException().localizedMessage
            else -> error.message!!
        }.apply {
            Log.e(tag, this)
        }
    }
}