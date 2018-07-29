package ru.ratanov.kinoman

import android.app.Application
import ru.ratanov.kinoman.managers.firebase.FirebaseManager

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseManager.init()
    }

}