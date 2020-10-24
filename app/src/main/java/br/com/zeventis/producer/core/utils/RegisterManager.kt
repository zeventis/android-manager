package br.com.zeventis.producer.core.utils

import android.content.Context
import android.content.SharedPreferences
import br.com.zeventis.producer.R
import br.com.zeventis.producer.core.utils.Constants.SharedPreferences.REGISTER
import br.com.zeventis.producer.presentation.model.register.Register
import com.google.gson.Gson

class RegisterManager(context: Context) {
    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    /**
     * Function to save register cache
     *
     * @param register user to save on device
     */
    fun saveRegister(register: Register) {
        val editor = sharedPreferences.edit()
        editor.putString(REGISTER, Gson().toJson(register))
        editor.apply()
    }

    /**
     * Function to get register cache
     *
     * @return register cache
     */
    fun getRegister(): Register =
        Gson().fromJson(sharedPreferences.getString(REGISTER, null), Register::class.java)
            ?: Register()


    /**
     * Function to clear register cache
     *
     */
    fun clearRegister() {
        val editor = sharedPreferences.edit()
        editor.putString(REGISTER, "")
        editor.apply()
    }
}