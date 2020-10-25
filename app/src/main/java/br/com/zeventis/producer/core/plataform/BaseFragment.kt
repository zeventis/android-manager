package br.com.zeventis.producer.core.plataform

import android.app.Activity
import android.os.Bundle
import android.text.method.DigitsKeyListener
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.zeventis.producer.core.network.AuthInterceptor
import br.com.zeventis.producer.core.network.ResponseError
import br.com.zeventis.producer.core.network.provideRetrofit
import com.redmadrobot.inputmask.MaskedTextChangedListener
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlinx.android.synthetic.main.fragment_register_company_data.loading
import okhttp3.ResponseBody
import org.json.JSONException
import org.koin.android.ext.android.inject
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Response

abstract class BaseFragment : Fragment() {

    private val authInterceptor: AuthInterceptor by inject()

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

    protected fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    protected fun maskField(
        editText: EditText?,
        formatMask: String,
        digits: String = "",
        inputType: Int = 0
    ) {
        if (editText != null) {
            if (inputType != 0) {
                editText.inputType = inputType
            }

            if (digits.isNotEmpty()) {
                editText.keyListener = DigitsKeyListener.getInstance(digits)
            }

            val listener = MaskedTextChangedListener(formatMask, editText)
            editText.addTextChangedListener(listener)
            editText.onFocusChangeListener = listener
        }
    }

    open fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    open fun hideLoading() {
        if (loading != null) {
            loading.visibility = View.GONE
        }
    }

    protected fun handleError(tag: String, exception: Exception) {
        hideLoading()
        val responseError: ResponseError? = convertResponseError(exception, tag)

        handleBackendError(responseError, tag)
    }

    // TODO Extract to other class
    private fun handleBackendError(responseError: ResponseError?, tag: String) {
        val errorList = responseError?.errorMessages
        if (errorList != null) {
            for (error in errorList) {
                Log.e(tag, "CODE: ${error.code} -----> ${error.message}")
                when (error.code) {
                    // TODO Implements code error back-end
                }
            }
        }
    }

    private fun convertResponseError(
        exception: Exception,
        tag: String
    ): ResponseError? {
        var responseError: ResponseError? = null
        val httpException: HttpException = exception as HttpException
        val converter: Converter<ResponseBody, ResponseError> =
            provideRetrofit(authInterceptor).responseBodyConverter(
                ResponseError::class.java,
                arrayOfNulls<Annotation>(0)
            )

        try {
            responseError = converter.convert(httpException.response()?.errorBody())
        } catch (e: Exception) {
            handleExceptions(exception, tag)
        }
        return responseError
    }

    private fun handleExceptions(exception: Exception, tag: String) {
        when (exception) {
            is UnknownHostException -> handleServerDown(tag)
            is IOException -> handleNetworkError(tag)
            is HttpException -> handleHttpException(tag, exception)
            is JSONException -> handleJsonException(tag)
            is SocketTimeoutException -> handleTimeoutError(tag)
            else -> handleGenericException(exception)
        }
    }

    private fun handleHttpException(tag: String, error: HttpException) {
        when (error.code()) {
            403 -> handleUnauthorized(tag)
            500 -> handleBackendError(tag, error.response())
            else -> handleGenericCode(error)
        }
    }

    // TODO Implements correctly handle error
    private fun handleJsonException(tag: String) {
        Log.e(tag, "JSON-ERROR")
    }

    // TODO Implements correctly handle error
    private fun handleTimeoutError(tag: String) {
        Log.e(tag, "TIMEOUT-ERROR")
    }

    // TODO Implements correctly handle error
    private fun handleServerDown(tag: String) {
        Log.e(tag, "SERVER-DOWN-ERROR")
    }

    // TODO Implements correctly handle error
    private fun handleGenericException(error: Throwable) {
        Log.e("GENERIC-ERROR", error.toString())
    }

    // TODO Implements correctly handle error
    private fun handleNetworkError(tag: String) {
        Log.e(tag, "NETWORK-ERROR")
    }

    // TODO Implements correctly handle error
    private fun handleGenericCode(error: HttpException) {
        Log.e("GENERIC-CODE-ERROR", error.toString())
    }

    // TODO Implements correctly handle error
    private fun handleBackendError(tag: String, response: Response<*>?) {
        Log.e(tag, response.toString())
    }

    // TODO Implements correctly handle error
    private fun handleUnauthorized(tag: String) {
        Log.e(tag, "UNAUTHORIZED-ERROR")
    }
}