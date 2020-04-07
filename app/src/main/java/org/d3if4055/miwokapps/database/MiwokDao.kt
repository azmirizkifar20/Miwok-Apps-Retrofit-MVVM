package org.d3if4055.miwokapps.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MiwokDao {

    @Query("SELECT * FROM miwok")
    fun getMiwokList(): LiveData<List<MiwokDatabaseEntities>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list : List<MiwokDatabaseEntities>)

}