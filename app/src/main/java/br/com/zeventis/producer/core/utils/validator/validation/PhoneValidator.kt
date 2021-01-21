package br.com.zeventis.producer.core.utils.validator.validation

import android.widget.EditText
import br.com.zeventis.producer.core.utils.validator.Validator

class PhoneValidator(
    private val editText: EditText,
    private val isRequiredField: Boolean = false,
    private val isSetErrorTrue: Boolean = true
) {

    private val text = editText.text

    init {
        this.
    }



    fun validation(): Boolean = text.matches(Regex(REGEX))


    companion object {
        const val REGEX = "^\\((?:[14689][1-9]|2[12478]|3[1234578]|5[1345]|7[134579])\\) (?:[2-8]|9[1-9])[0-9]{3}-[0-9]{4}\$\n"
    }
}