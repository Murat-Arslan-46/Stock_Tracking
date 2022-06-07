package com.marslan.stocktracking.core.helper

import android.content.Context
import androidx.core.content.edit
import com.marslan.stocktracking.core.extension.ignoreNull
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesHelper @Inject constructor() {

    @Inject
    lateinit var context: Context

    var remoteDB: String
        get() = context.getSharedPreferences(remote_preferences_key, Context.MODE_PRIVATE)
            .getString(access_remoteDB_key, "").ignoreNull()
        set(value) {
            context.getSharedPreferences(remote_preferences_key, Context.MODE_PRIVATE)
                .edit {
                    putString(access_remoteDB_key, value)
                }
        }

    companion object {

        const val remote_preferences_key = "token_preferences"
        const val access_remoteDB_key = "access_token"

    }

}