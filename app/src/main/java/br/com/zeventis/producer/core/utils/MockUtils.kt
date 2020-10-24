package br.com.zeventis.producer.core.utils

import android.content.Context

class MockUtils {

    fun openStringFile(context: Context, fileName: String): String {
        val inputStream = context.assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)

        inputStream.read(buffer)
        inputStream.close()

        return String(buffer)
    }
}