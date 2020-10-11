package br.com.zeventis.managerapp.core.plataform

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.irozon.sneaker.Sneaker
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import retrofit2.HttpException
import retrofit2.Response

abstract class BaseFragment : Fragment() {

    protected abstract fun getContentLayoutId(): Int
    protected abstract fun init()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        Log.e(tag, error.toString())

        when (error) {
            is UnknownHostException -> handleServerDown(tag)
            is IOException -> handleNetworkError(tag)
            is SocketTimeoutException -> handleTimeoutError(tag)
            is HttpException -> handleHttpException(tag, error)
            else -> handleGenericException(error)
        }
    }

    // TODO Implements correctly handle error
    private fun handleTimeoutError(tag: String) {
        Log.e(tag, "TIMEOUT-ERROR")
    }

    private fun handleHttpException(tag: String, error: HttpException) {
        when (error.code()) {
            401 -> handleUnauthorized(tag)
            500 -> handleBackendError(tag, error.response())
            else -> handleGenericCode(error)
        }
    }

    // TODO Implements correctly handle error
    private fun handleServerDown(tag: String) {
        Log.e(tag, "SERVER-DOWN-ERROR")
    }

    // TODO Implements correctly handle error
    private fun handleGenericException(error: Throwable) {
        Sneaker.with(requireActivity())
            .setTitle("Erro")
            .setMessage(error.message.toString())
            .setCornerRadius(64, 32)
            .setDuration(10000)
            .autoHide(true)
            .sneakError()
        Log.e("GENERIC-ERROR", error.toString())
    }

    // TODO Implements correctly handle error
    private fun handleNetworkError(tag: String) {
        Log.e(tag, "NETWORK-ERROR")
    }

    // TODO Implements correctly handle error
    private fun handleGenericCode(error: HttpException) {
        Sneaker.with(requireActivity())
            .setTitle("Erro")
            .setMessage(error.message.toString())
            .setCornerRadius(64, 32)
            .setDuration(10000)
            .autoHide(true)
            .sneakError()
        Log.e("GENERIC-CODE-ERROR", error.toString())
    }

    // TODO Implements correctly handle error
    private fun handleBackendError(tag: String, response: Response<*>?) {
        response?.message()?.let {
            Sneaker.with(requireActivity())
                .setTitle("Erro")
                .setMessage(it)
                .setCornerRadius(64, 32)
                .setDuration(10000)
                .autoHide(true)
                .sneakError()
        }
        Log.e(tag, response.toString())
    }

    // TODO Implements correctly handle error
    private fun handleUnauthorized(tag: String) {
        Log.e(tag, "UNAUTHORIZED-ERROR")
    }
}