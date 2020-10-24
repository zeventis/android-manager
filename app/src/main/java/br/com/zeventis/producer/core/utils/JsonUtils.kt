package br.com.zeventis.producer.core.utils

import android.content.Context
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream

class JsonUtils {

    companion object {
        val gson = Gson()

        @Throws(IOException::class)
        fun <T> parseJsonToObject(context: Context, stringFile: String?, classOfT: Class<T>?): T {
            val stringData = openStringFile(context, stringFile)
            return gson.fromJson(stringData, classOfT)
        }

        @Throws(IOException::class)
        fun openStringFile(context: Context, fileName: String?): String {
            val inputStream: InputStream = context.assets.open(fileName!!)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            return String(buffer)
        }

    }
}