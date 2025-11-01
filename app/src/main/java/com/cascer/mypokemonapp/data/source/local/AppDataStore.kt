package com.cascer.mypokemonapp.data.source.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_datastore")

class AppDataStore @Inject constructor(
    private val context: Context
) {
    companion object {
        private val KEY_IS_LOGIN = booleanPreferencesKey("is_login")
        private val KEY_USERNAME = stringPreferencesKey("username")
    }

    val isLogin = context.dataStore.data.map { preferences ->
        preferences[KEY_IS_LOGIN] ?: false
    }
    val username = context.dataStore.data.map { preferences ->
        preferences[KEY_USERNAME] ?: ""
    }

    suspend fun login(username: String, isLogin: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[KEY_USERNAME] = username
            preferences[KEY_IS_LOGIN] = isLogin
        }
    }

    suspend fun clearSession() {
        context.dataStore.edit { it.clear() }
    }
}