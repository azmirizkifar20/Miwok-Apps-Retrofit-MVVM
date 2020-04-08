package org.d3if4055.miwokapps.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.d3if4055.miwokapps.model.DB_NAME

@Suppress("SpellCheckingInspection")
@Database(entities = [MiwokDatabaseModel::class], version = 1, exportSchema = false)
abstract class MiwokDatabase : RoomDatabase() {

    abstract val miwokDao: MiwokDao

    companion object {
        @Volatile
        private var INSTANCE: MiwokDatabase? = null

        fun getInstance(context: Context) : MiwokDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MiwokDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}