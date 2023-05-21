package ort.tp3

import android.app.Application
import ort.tp3.database.AppDatabase
import ort.tp3.database.DatabaseSingleton

class App : Application() {
    lateinit var appDatabase: AppDatabase

    override fun onCreate() {
        super.onCreate()
        appDatabase = AppDatabase.getInstance(this)

        DatabaseSingleton.db = appDatabase
    }
}
