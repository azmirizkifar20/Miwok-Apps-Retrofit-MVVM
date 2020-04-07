package org.d3if4055.miwokapps

import android.app.Application
import android.os.Build
import androidx.work.*
import org.d3if4055.miwokapps.work.RefreshDataWork
import java.util.concurrent.TimeUnit

class MiwokApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        delayedInit()
    }

    private fun delayedInit() {
        setUpReccuringNetwork()
    }

    private fun setUpReccuringNetwork() {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.METERED)
            .setRequiresCharging(true)
            .apply {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    setRequiresDeviceIdle(true)
                }
            }.build()
        val repeatingReq = PeriodicWorkRequestBuilder<RefreshDataWork>(
            1,
            TimeUnit.DAYS
        )
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance().enqueueUniquePeriodicWork(
            RefreshDataWork.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingReq
        )
    }
}