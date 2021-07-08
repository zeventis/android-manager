package br.com.zeventis.producer.presentation.ui.addevent

import android.app.Activity
import android.app.AlertDialog
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import br.com.zeventis.producer.R
import br.com.zeventis.producer.core.plataform.BaseFragment
import br.com.zeventis.producer.core.utils.Constants
import br.com.zeventis.producer.core.utils.extensions.formatDateToBackendFormat
import br.com.zeventis.producer.presentation.model.addevent.AddEventRequestPresentation
import java.math.BigDecimal
import kotlinx.android.synthetic.main.custom_dialog_event_code.view.dialogEventClipboardIv
import kotlinx.android.synthetic.main.custom_dialog_event_code.view.dialogEventCloseBt
import kotlinx.android.synthetic.main.custom_dialog_event_code.view.dialogEventCodeTv
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentBackBt
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentDateIl
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentDefaultActionsPerPromoterIl
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentDoneBtn
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentDoneLoadingPb
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentHourIl
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentMinimumActionsPerPromoterIl
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentNameIl
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentTicketPriceIl
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentUploadPhotoBt
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentUploadPhotoButtonIv
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentUploadPhotoIv
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentUploadTitleTv
import org.koin.android.ext.android.inject


class AddEventFragment : BaseFragment() {

    private val addEventViewModel: AddEventViewModel by inject()
    private lateinit var base64Url: String

    override fun getContentLayoutId(): Int = R.layout.fragment_add_event

    override fun init() {
        initMask()
        observeViewModelEvents()
        observeViewModelStates()
        initOnClickListeners()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == LOAD_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            activity?.let { addEventViewModel.tryToConvertImage(data, it) }
        }
    }

    private fun initMask() {
        maskField(addEventFragmentDateIl.editText, Constants.Mask.DATE, Constants.MaskDigits.DATE, InputType.TYPE_CLASS_NUMBER)
        maskField(addEventFragmentHourIl.editText, Constants.Mask.HOUR, Constants.MaskDigits.HOUR, InputType.TYPE_CLASS_NUMBER)
        maskField(addEventFragmentTicketPriceIl.editText, Constants.Mask.MONEY, Constants.MaskDigits.MONEY, InputType.TYPE_CLASS_NUMBER)

    }

    private fun initOnClickListeners() {
        addEventFragmentBackBt.setOnClickListener { activity?.onBackPressed() }
        addEventFragmentDoneBtn.setOnClickListener { handleDoneButton() }
        addEventFragmentUploadPhotoButtonIv.setOnClickListener { handleUploadPhotoButton() }
        addEventFragmentUploadPhotoIv.setOnClickListener { handleUploadPhotoButton() }
    }

    private fun handleUploadPhotoButton() {
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, LOAD_IMAGE_REQUEST_CODE)
    }

    private fun togglePhotoViews(selectedImage: Bitmap?) {
        addEventFragmentUploadPhotoIv.setImageBitmap(selectedImage)
        addEventFragmentUploadPhotoIv.visibility = View.VISIBLE
        addEventFragmentUploadPhotoBt.visibility = View.INVISIBLE
        addEventFragmentUploadPhotoButtonIv.visibility = View.INVISIBLE
        addEventFragmentUploadTitleTv.visibility = View.INVISIBLE
    }

    private fun handleDoneButton() {
        val addEventRequestPresentation = AddEventRequestPresentation(
            name = addEventFragmentNameIl.editText?.text.toString(),
            date = transformDate(),
            base64Image = base64Url,
            defaultActionsPerPromoter = Integer.parseInt(
                addEventFragmentDefaultActionsPerPromoterIl.editText?.text.toString()
            ),
            ticketPrice = formatTicketPriceToBigDecimal(),
            minimumActionsRequiredPerPromoter = Integer.parseInt(
                addEventFragmentMinimumActionsPerPromoterIl.editText?.text.toString()
            )
        )

        addEventViewModel.addEvent(addEventRequestPresentation)
    }

    private fun formatTicketPriceToBigDecimal() = BigDecimal(
        addEventFragmentTicketPriceIl.editText?.text.toString()
            .replace("R$ ", "")
            .replace(",", ".")
    )

    private fun transformDate(): String {
        val dateString = addEventFragmentDateIl.editText?.text.toString().formatDateToBackendFormat()
        val hourString = addEventFragmentHourIl.editText?.text.toString()

        return "${dateString}T${hourString}"
    }

    private fun observeViewModelEvents() {
        addEventViewModel.viewEvent.observe(viewLifecycleOwner, {
            when (it) {
                is AddEventViewEvents.OnSaveEventSuccess -> handleSaveEventSuccess(it.event.eventCode)
                is AddEventViewEvents.OnConvertImageSuccess -> {
                    handleCovertImage(it)
                }
                is AddEventViewEvents.OnSaveEventFailed, -> handleError(this::class.java.toString(), it.exception)
                is AddEventViewEvents.OnConvertImageError -> handleError(this::class.java.toString(), it.exception)
            }
        })
    }

    private fun handleCovertImage(it: AddEventViewEvents.OnConvertImageSuccess) {
        base64Url = it.base64Url
        togglePhotoViews(it.selectedImage)
    }

    private fun observeViewModelStates() {
        addEventViewModel.viewState.observe(viewLifecycleOwner, {
            when (it) {
                is AddEventViewState.ShowLoading -> showLoading()
                is AddEventViewState.HideLoading -> hideLoading(it.success)
            }
        })
    }

    private fun showLoading() {
        addEventFragmentDoneLoadingPb.visibility = View.VISIBLE
        addEventFragmentDoneBtn.visibility = View.GONE
    }

    private fun hideLoading(success: Boolean) {
        if (!success) addEventFragmentDoneBtn.visibility = View.VISIBLE
        addEventFragmentDoneLoadingPb.visibility = View.GONE
    }

    private fun handleSaveEventSuccess(eventCode: String) {
        val messageBoxView =
            LayoutInflater.from(activity).inflate(R.layout.custom_dialog_event_code, null)
        val messageBoxBuilder = AlertDialog.Builder(activity).setView(messageBoxView)
        messageBoxView.dialogEventCodeTv.text = eventCode
        val messageBoxInstance = messageBoxBuilder.show()

        messageBoxView.dialogEventClipboardIv.setOnClickListener { setClipboard(eventCode) }
        messageBoxView.dialogEventCloseBt.setOnClickListener {
            messageBoxInstance.dismiss()
            activity?.onBackPressed()
        }
    }

    private fun setClipboard(eventCode: String) {
        val clipboard =
            context?.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
        val clip =
            ClipData.newPlainText(
                "Código do evento",
                eventCode
            ) // TODO Add friendly user message to send on whatsapp
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "Código copiado: $eventCode", Toast.LENGTH_LONG).show() // TODO Send string to resource strings
    }

    companion object {
        fun newInstance() = AddEventFragment()

        private const val LOAD_IMAGE_REQUEST_CODE = 342
    }
}