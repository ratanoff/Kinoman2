package ru.ratanov.kinoman.managers.firebase

import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import ru.ratanov.kinoman.managers.update.ForceUpdateChecker
import java.util.HashMap

object FirebaseManager {

    private val TAG = FirebaseManager.javaClass.simpleName

    fun init() {
        val firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

        // set in-app defaults
        val remoteConfigDefaults = HashMap<String, Any>()
        remoteConfigDefaults[ForceUpdateChecker.KEY_UPDATE_REQUIRED] = false

        firebaseRemoteConfig.setDefaults(remoteConfigDefaults)
        firebaseRemoteConfig.fetch(60)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "remote config is fetched")
                        firebaseRemoteConfig.activateFetched()
                    }
                }
    }
}