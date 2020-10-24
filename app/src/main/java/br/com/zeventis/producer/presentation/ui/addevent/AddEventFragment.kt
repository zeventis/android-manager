package br.com.zeventis.producer.presentation.ui.addevent

import android.app.Activity
import android.app.AlertDialog
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import br.com.zeventis.producer.R
import br.com.zeventis.producer.core.components.MaskWatcher
import br.com.zeventis.producer.core.plataform.BaseFragment
import br.com.zeventis.producer.core.utils.extensions.formatDateToBackendFormat
import br.com.zeventis.producer.presentation.model.addevent.AddEventRequestPresentation
import com.irozon.sneaker.Sneaker
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream
import java.math.BigDecimal
import kotlinx.android.synthetic.main.custom_dialog_event_code.view.dialogEventClipboardIv
import kotlinx.android.synthetic.main.custom_dialog_event_code.view.dialogEventCloseBt
import kotlinx.android.synthetic.main.custom_dialog_event_code.view.dialogEventCodeTv
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentBackBt
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentDateIl
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentDefaultActionsPerPromoterIl
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentDoneBtn
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentHourIl
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentMinimumActionsPerPromoterIl
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentNameIl
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentTicketPriceIl
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentUploadPhotoBt
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentUploadPhotoIv
import org.koin.android.ext.android.inject


class AddEventFragment : BaseFragment() {

    private val addEventViewModel: AddEventViewModel by inject()
    private var base64Url: String = ""

    override fun getContentLayoutId(): Int = R.layout.fragment_add_event

    override fun init() {
        initMask()
        observeViewModelEvents()
        initOnClickListeners()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == LOAD_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            tryToConvertImage(data)
        }
    }

    private fun initMask() {
        addEventFragmentDateIl.editText?.addTextChangedListener(MaskWatcher(MaskWatcher.DATE_MASK))
        addEventFragmentHourIl.editText?.addTextChangedListener(MaskWatcher(MaskWatcher.HOUR_MASK))
    }

    private fun initOnClickListeners() {
        addEventFragmentBackBt.setOnClickListener { activity?.onBackPressed() }
        addEventFragmentDoneBtn.setOnClickListener { handleDoneButton() }
        addEventFragmentUploadPhotoBt.setOnClickListener { handleUploadPhotoButton() }
        addEventFragmentUploadPhotoIv.setOnClickListener { handleUploadPhotoButton() }
    }

    private fun handleUploadPhotoButton() {
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, LOAD_IMAGE_REQUEST_CODE)
    }

    // TODO Refactor to view model
    private fun tryToConvertImage(data: Intent?) {
        try {
            val selectedImage = decodeImage(data)
            togglePhotoViews(selectedImage)
            savePhotoIntoRequest(selectedImage)
        } catch (exception: FileNotFoundException) {
            Log.e(AddEventFragment::class.java.toString(), exception.toString())
            Sneaker.with(this)
                .setTitle("Error!!")
                .setMessage("Ocorreu um erro ao encontrar a imagem, tente novamente") // TODO Refactor to strings
                .sneakError()
        }
    }

    // TODO Refactor to view model
    private fun decodeImage(data: Intent?): Bitmap? {
        val imageUri: Uri? = data?.data
        val imageStream: InputStream? =
            activity?.contentResolver?.openInputStream(imageUri!!)
        return BitmapFactory.decodeStream(imageStream)
    }

    // TODO Refactor to view model
    private fun savePhotoIntoRequest(selectedImage: Bitmap?) {
        val byteArrayOutputStream = ByteArrayOutputStream()
        selectedImage?.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream)
        val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
        base64Url = Base64.encodeToString(byteArray, Base64.NO_WRAP)
    }

    private fun togglePhotoViews(selectedImage: Bitmap?) {
        addEventFragmentUploadPhotoIv.setImageBitmap(selectedImage)
        addEventFragmentUploadPhotoIv.visibility = View.VISIBLE
        addEventFragmentUploadPhotoBt.visibility = View.GONE
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
        addEventViewModel.viewState.observe(viewLifecycleOwner, {
            when (it) {
                is AddEventViewEvents.OnSaveEventSuccess -> handleSaveEventSuccess(it.event.eventCode)
                is AddEventViewEvents.OnSaveEventFailed -> handleError(
                    this::class.java.toString(),
                    it.exceptionError
                )
            }
        })
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
        Toast.makeText(context, "Código copiado: $eventCode", Toast.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance() = AddEventFragment()

        private const val LOAD_IMAGE_REQUEST_CODE = 342
    }
}