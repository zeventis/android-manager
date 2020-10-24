package br.com.zeventis.producer.core.network

import android.content.Context
import android.content.SharedPreferences
import br.com.zeventis.producer.R
import br.com.zeventis.producer.core.utils.Constants.SharedPreferences.USER
import br.com.zeventis.producer.presentation.model.authentication.User
import com.google.gson.Gson

class SessionManager(context: Context) {
    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    /**
     * Function to save user session
     *
     * @param user usuer to save on session
     */
    fun saveUser(user: User) {
        val editor = sharedPreferences.edit()
        editor.putString(USER, Gson().toJson(user))
        editor.apply()
    }

    /**
     * Function to get user session
     *
     * @return user logged instance
     */
    fun getUser(): User? {
        return Gson().fromJson(sharedPreferences.getString(USER, null), User::class.java)
    }

    fun getTwoFirstNameUser(): String? {
        var name = getUser()?.name

        val spitedName = getUser()?.name?.split(" ")

        if (spitedName != null && spitedName.size > 1) {
            name = "${spitedName[0]} ${spitedName[1]}"
        }

        return name
    }

}