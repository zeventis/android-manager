package br.com.zeventis.managerapp.core.customview

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import br.com.zeventis.managerapp.R

class CustomDialogCode(context: Context, private val eventCode: String) : Dialog(context),
    View.OnClickListener {

    init {
        setCancelable(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.custom_dialog_event_code)


    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

}