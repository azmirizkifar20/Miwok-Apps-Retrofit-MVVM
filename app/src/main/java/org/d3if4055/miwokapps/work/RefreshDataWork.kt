package org.d3if4055.miwokapps.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import org.d3if4055.miwokapps.database.getDatabase
import org.d3if4055.miwokapps.repository.MiwokRepository
import retrofit2.HttpException

class RefreshDataWork(
    appContext : Context, params : WorkerParameters
) : CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = MiwokRepository(database)

        return try{
            repository.refreshMiwokList()
            Result.success()
        }catch (e : HttpException){
            Result.failure()
        }
    }
}