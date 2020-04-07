package org.d3if4055.miwokapps.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.d3if4055.miwokapps.data.Miwok
import org.d3if4055.miwokapps.database.MiwokDatabase
import org.d3if4055.miwokapps.database.asDatabaseModel
import org.d3if4055.miwokapps.database.asDomainModel
import org.d3if4055.miwokapps.network.MiwokApi
import retrofit2.await

class MiwokRepository(private val database: MiwokDatabase) {

    val miwokList : LiveData<List<Miwok>> = Transformations.map(database.miwokDao.getMiwokList()){
        it.asDomainModel()
    }

    suspend fun refreshMiwokList(){
        withContext(Dispatchers.IO){
            val miwokList = MiwokApi.retrofitService.showList().await()
            database.miwokDao.insertAll(miwokList.asDatabaseModel())
        }
    }

}