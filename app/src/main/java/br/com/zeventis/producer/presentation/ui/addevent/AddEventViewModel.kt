package br.com.zeventis.producer.presentation.ui.addevent

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zeventis.producer.core.plataform.BaseViewModel
import br.com.zeventis.producer.domain.usecase.AddEventUseCase
import br.com.zeventis.producer.presentation.mapper.addevent.AddEventRequestMapper
import br.com.zeventis.producer.presentation.mapper.addevent.AddEventResponseMapper
import br.com.zeventis.producer.presentation.model.addevent.AddEventRequestPresentation
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream
import kotlinx.coroutines.launch

class AddEventViewModel(
    private val addEventUseCase: AddEventUseCase
) : BaseViewModel() {

    private var event = MutableLiveData<AddEventViewEvents>()
    val viewEvent: LiveData<AddEventViewEvents> = event

    private var state = MutableLiveData<AddEventViewState>()
    val viewState: LiveData<AddEventViewState> = state

    fun addEvent(addEventRequestPresentation: AddEventRequestPresentation) {
        viewModelScope.launch {
            try {
                state.value = AddEventViewState.ShowLoading()

                val response = addEventUseCase.execute(AddEventRequestMapper.transformFrom(addEventRequestPresentation))

                event.value = AddEventViewEvents.OnSaveEventSuccess(
                    AddEventResponseMapper.transformTo(response)
                )
                state.value = AddEventViewState.HideLoading(true)
            } catch (exception: Exception) {
                event.value = AddEventViewEvents.OnSaveEventFailed(exception)
                state.value = AddEventViewState.HideLoading(false)
            }
        }
    }

    fun tryToConvertImage(data: Intent?, activity: Activity) {
        try {
            val selectedImage = decodeImage(data, activity)
            selectedImage?.let { savePhotoIntoRequest(it) }
        } catch (exception: FileNotFoundException) {
            event.value = AddEventViewEvents.OnConvertImageError(exception)
        }
    }

    private fun decodeImage(data: Intent?, activity: Activity): Bitmap? {
        val imageUri: Uri? = data?.data
        val imageStream: InputStream? =
            activity.contentResolver.openInputStream(imageUri!!)
        return BitmapFactory.decodeStream(imageStream)
    }

    private fun savePhotoIntoRequest(selectedImage: Bitmap) {
        val byteArrayOutputStream = ByteArrayOutputStream()
        selectedImage.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream)
        val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
        val base64Url = Base64.encodeToString(byteArray, Base64.NO_WRAP)
        event.value = AddEventViewEvents.OnConvertImageSuccess(base64Url, selectedImage)
    }
}