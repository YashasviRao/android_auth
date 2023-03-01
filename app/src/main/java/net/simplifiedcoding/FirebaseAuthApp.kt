package net.simplifiedcoding

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import net.simplifiedcoding.data.AppContainer
import net.simplifiedcoding.data.AppDataContainer

@HiltAndroidApp
class FirebaseAuthApp : Application(){
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}