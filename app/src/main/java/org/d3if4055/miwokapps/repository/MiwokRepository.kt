package org.d3if4055.miwokapps.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.d3if4055.miwokapps.model.Miwok
import org.d3if4055.miwokapps.database.MiwokDatabase
import org.d3if4055.miwokapps.database.convertToDatabaseModel
import org.d3if4055.miwokapps.database.convertToDomainModel
import org.d3if4055.miwokapps.network.MiwokApi
import retrofit2.await

@Suppress("SpellCheckingInspection")
class MiwokRepository(private val database: MiwokDatabase) {

    val miwok: LiveData<List<Miwok>> = Transformations.map(database.miwokDao.getMiwok()) {
        convertToDomainModel(it)
    }

    suspend fun refreshMiwok() {
        withContext(Dispatchers.IO) {
            val miwok = MiwokApi.retrofitService.showList().await()
            database.miwokDao.insertAll(convertToDatabaseModel(miwok))
        }
    }

}