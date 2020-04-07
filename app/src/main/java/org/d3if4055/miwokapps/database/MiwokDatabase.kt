package org.d3if4055.miwokapps.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MiwokDatabaseEntities::class],version = 1)
abstract class MiwokDatabase : RoomDatabase() {
    abstract val miwokDao : MiwokDao
}

private lateinit var INSTANCE : MiwokDatabase

fun getDatabase(context: Context) : MiwokDatabase{

    synchronized(MiwokDatabase::class.java){
        if(!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                MiwokDatabase::class.java,
                "miwok_database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }
        return INSTANCE
    }
}