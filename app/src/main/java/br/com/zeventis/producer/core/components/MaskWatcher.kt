package br.com.zeventis.producer.core.components

import android.text.Editable
import android.text.TextWatcher

class MaskWatcher(private val mask: String) : TextWatcher {
    private var isRunning = false
    private var isDeleting = true

    override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {
        isDeleting = count > after
    }

    override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {}
    override fun afterTextChanged(editable: Editable) {
        if (isRunning || isDeleting) {
            return
        }
        isRunning = true

        val editableLength: Int = editable.length
        if (editableLength < mask.length) {
            if (mask[editableLength] != '#') {
                editable.append(mask[editableLength])
            } else if (mask[editableLength - 1] != '#') {
                editable.insert(editableLength - 1, mask, editableLength - 1, editableLength)
            }
        }

        isRunning = false
    }

    companion object {
        const val PHONE_MASK = "(##) #####-####"
        const val CEP_MASK = "#####-###"
        const val DATE_MASK = "##/##/####"
        const val MONEY_MASK = "R$ ###,##"
        const val HOUR_MASK = "##:##"
    }
}


//package br.com.zeventis.producer.core.components
//
//import android.text.Editable
//import android.text.TextWatcher
//
//class MaskWatcher(private val mask: String) : TextWatcher {
//    private var isRunning = true
//    private var oldTxt = ""
//
//    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//        val str: String = unmask(s.toString())
//        var maskCurrent = ""
//        if (isRunning) {
//            oldTxt = str
//            isRunning = false
//            return
//        }
//        var i = 0
//        for (m in mask.toCharArray()) {
//            if (m != '#' && str.length > oldTxt.length) {
//                maskCurrent += m
//                continue
//            }
//            maskCurrent += try {
//                str[i]
//            } catch (e: Exception) {
//                break
//            }
//            i++
//        }
//        isRunning = true
//        et.setText(maskCurrent)
//        et.setSelection(maskCurrent.length)
//    }
//
//    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//
//    private fun unmask(s: String): String {
//        return s.replace("[.]".toRegex(), "").replace("[-]".toRegex(), "")
//            .replace("[/]".toRegex(), "").replace("[(]".toRegex(), "")
//            .replace("[)]".toRegex(), "")
//    }
//
//    override fun afterTextChanged(s: Editable?) {}
//
//    companion object {
//        const val PHONE_MASK = "(##) #####-####"
//        const val CEP_MASK = "#####-###"
//        const val DATE_MASK = "##/##/####"
//        const val MONEY_MASK = "R$ ###,##"
//        const val HOUR_MASK = "##:##"
//    }
//}