package br.com.zeventis.managerapp.presentation.ui.addevent

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
import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.core.plataform.BaseFragment
import br.com.zeventis.managerapp.core.utils.extensions.formatDate
import br.com.zeventis.managerapp.presentation.model.addevent.AddEventRequestPresentation
import com.irozon.sneaker.Sneaker
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream
import kotlinx.android.synthetic.main.custom_dialog_event_code.view.dialogEventClipboardIv
import kotlinx.android.synthetic.main.custom_dialog_event_code.view.dialogEventCloseBt
import kotlinx.android.synthetic.main.custom_dialog_event_code.view.dialogEventCodeTv
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentBackBt
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentDateEl
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentDefaultActionsPerPromoterEl
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentDoneBtn
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentHourEl
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentMinimumActionsPerPromoterEl
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentNameEl
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentUploadPhotoBt
import kotlinx.android.synthetic.main.fragment_add_event.addEventFragmentUploadPhotoIv
import org.koin.android.ext.android.inject


class AddEventFragment : BaseFragment() {

    private val addEventViewModel: AddEventViewModel by inject()
    private var base64Url: String = ""

    override fun getContentLayoutId(): Int = R.layout.fragment_add_event

    override fun init() {
        observeViewModelEvents()
        initOnClickListeners()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == LOAD_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            tryToConvertImage(data)
        }
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
        selectedImage?.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream)
        val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
        val base64Encoded: String = Base64.encodeToString(byteArray, Base64.DEFAULT)
        base64Url = base64Encoded
    }

    private fun togglePhotoViews(selectedImage: Bitmap?) {
        addEventFragmentUploadPhotoIv.setImageBitmap(selectedImage)
        addEventFragmentUploadPhotoIv.visibility = View.VISIBLE
        addEventFragmentUploadPhotoBt.visibility = View.GONE
    }

    private fun handleDoneButton() {
        val dateTime = transformDate()
        val addEventRequestPresentation = AddEventRequestPresentation(
            name = addEventFragmentNameEl.editText?.text.toString(),
            date = dateTime,
            base64Image = base64Url,
            defaultActionsPerPromoter = Integer.parseInt(
                addEventFragmentDefaultActionsPerPromoterEl.editText?.text.toString()
            ),
            minimumActionsRequiredPerPromoter = Integer.parseInt(
                addEventFragmentMinimumActionsPerPromoterEl.editText?.text.toString()
            )
        )

        addEventViewModel.addEvent(addEventRequestPresentation)

    }

    private fun transformDate(): String {
        val dateString = addEventFragmentDateEl.editText?.text.toString().formatDate()
        val hourString = addEventFragmentHourEl.editText?.text.toString()

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
            ClipData.newPlainText("Código do evento", eventCode) // TODO Add friendly user message
        clipboard.setPrimaryClip(clip)
    }

    companion object {
        fun newInstance() = AddEventFragment()

        private const val LOAD_IMAGE_REQUEST_CODE = 342
    }
}