package ru.ratanov.kinoman

import android.app.Application
import ru.ratanov.kinoman.managers.firebase.FirebaseManager

class App : Application() {

    companion object {
        private var instance: Application? = null
        fun instance() = instance!!
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        FirebaseManager.init()
    }

}