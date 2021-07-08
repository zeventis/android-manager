package br.com.zeventis.producer.presentation.ui.addevent

import android.graphics.Bitmap
import br.com.zeventis.producer.presentation.model.addevent.AddEventResponsePresentation
import java.io.FileNotFoundException

open class AddEventViewEvents {

    data class OnSaveEventSuccess(val event: AddEventResponsePresentation) :
        AddEventViewEvents()

    data class OnSaveEventFailed(val exception: Exception) : AddEventViewEvents()
    data class OnConvertImageSuccess(val base64Url: String, val selectedImage: Bitmap) : AddEventViewEvents()
    data class OnConvertImageError(val exception: FileNotFoundException) : AddEventViewEvents()
}