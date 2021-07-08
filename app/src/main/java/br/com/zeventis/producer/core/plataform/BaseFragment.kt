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
import com.irozon.sneaker.Sneaker
import com.redmadrobot.inputmask.MaskedTextChangedListener
import java.io.FileNotFoundException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import okhttp3.ResponseBody
import org.json.JSONException
import org.koin.android.ext.android.inject
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Response
import javax.net.ssl.HttpsURLConnection

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

    protected fun handleError(tag: String, exception: Exception) {
        val responseError: ResponseError? = convertResponseError(exception, tag)
        handleBackendError(responseError, tag)
    }

    // TODO Extract to class network handle error
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

    // TODO Extract to class network handle error
    fun convertResponseError(exception: Exception, tag: String): ResponseError? {
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
        } finally {
            return responseError
        }
    }

    // TODO Extract to class network handle error
    private fun handleExceptions(exception: Exception, tag: String) {
        when (exception) {
            is UnknownHostException -> handleServerDown(tag)
            is IOException -> handleNetworkError(tag)
            is HttpException -> handleHttpException(tag, exception)
            is JSONException -> handleJsonException(tag)
            is SocketTimeoutException -> handleTimeoutException(tag)
            is FileNotFoundException -> handleFileNotFoundException()
            else -> handleGenericException(exception)
        }
    }

    // TODO Extract to class network handle error
    private fun handleFileNotFoundException() {
        activity?.let {
            Sneaker.with(it)
                .setTitle("Error!!")
                .setMessage("Ocorreu um erro ao encontrar a imagem, tente novamente") // TODO Refactor to strings
                .sneakError()
        }
    }

    // TODO Extract to class network handle error
    private fun handleHttpException(tag: String, error: HttpException) {
        when (error.code()) {
            HttpsURLConnection.HTTP_UNAUTHORIZED -> handleUnauthorized(tag)
            HttpsURLConnection.HTTP_INTERNAL_ERROR -> handleBackendError(tag, error.response())
            else -> handleGenericCode(error)
        }
    }

    // TODO Extract to class network handle error
    // TODO Implements correctly handle error
    private fun handleJsonException(tag: String) {
        Log.e(tag, "JSON-ERROR")
    }

    // TODO Extract to class network handle error
    // TODO Implements correctly handle error
    private fun handleTimeoutException(tag: String) {
        Log.e(tag, "TIMEOUT-ERROR")
    }

    // TODO Extract to class network handle error
    // TODO Implements correctly handle error
    private fun handleServerDown(tag: String) {
        Log.e(tag, "SERVER-DOWN-ERROR")
    }

    // TODO Extract to class network handle error
    // TODO Implements correctly handle error
    private fun handleGenericException(error: Throwable) {
        Log.e("GENERIC-ERROR", error.toString())
    }

    // TODO Extract to class network handle error
    // TODO Implements correctly handle error
    private fun handleNetworkError(tag: String) {
        Log.e(tag, "NETWORK-ERROR")
    }

    // TODO Extract to class network handle error
    // TODO Implements correctly handle error
    private fun handleGenericCode(error: HttpException) {
        Log.e("GENERIC-CODE-ERROR", error.toString())
    }

    // TODO Extract to class network handle error
    // TODO Implements correctly handle error
    private fun handleBackendError(tag: String, response: Response<*>?) {
        Log.e(tag, response.toString())
    }

    // TODO Extract to class network handle error
    // TODO Implements correctly handle error
    private fun handleUnauthorized(tag: String) {
        Log.e(tag, "UNAUTHORIZED-ERROR")
    }
}