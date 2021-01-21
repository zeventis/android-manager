package br.com.zeventis.producer.core.utils.validator

import android.widget.EditText

open class Validator<T>(
    private val editText: EditText,
    private val isRequiredField: Boolean = false,
    private val isSetErrorTrue: Boolean = true,
    private val classe: T
) {

    private val text = editText.text
    var isValid: Boolean = true

    init {
        if (isRequiredField) {
            requiredField()
        } else {

        }
    }

    private fun requiredField() {
        if (text.isNullOrEmpty()) {
            if (isSetErrorTrue) {
                editText.error = "Campo Obrigatório"
                isValid = false
            }
        }
    }
}