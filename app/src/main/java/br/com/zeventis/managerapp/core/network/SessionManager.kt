package br.com.zeventis.managerapp.core.network

import android.content.Context
import android.content.SharedPreferences
import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.presentation.model.Producer
import br.com.zeventis.managerapp.presentation.model.User
import com.google.gson.Gson

class SessionManager(context: Context) {
    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER = "user"
    }

    /**
     * Function to save user
     */
    fun saveUser(user: User) {
        val editor = sharedPreferences.edit()
        editor.putString(USER, Gson().toJson(user))
        editor.apply()
    }

    /**
     * Function to get user
     *
     * @return user logged instance or default user with
     */
    fun getUser(): User {
        val defaultUser = User("", "", "", "", "", "", "", "", Producer("", "", "", ""))
        val getUserJson = Gson().fromJson(sharedPreferences.getString(USER, null), User::class.java)
        return getUserJson ?: defaultUser
    }
}