package org.d3if4055.miwokapps.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.d3if4055.miwokapps.data.Miwok

@Entity(tableName = "miwok")
data class MiwokDatabaseEntities (
    val category: String,
    val background: String,
    val defaultWord: String,
    @PrimaryKey
    val miwokWord: String,
    val image: String = ""
)

fun List<MiwokDatabaseEntities>.asDomainModel() : List<Miwok> {
    return map {
        Miwok(
            category = it.category,
            background = it.background,
            defaultWord = it.defaultWord,
            miwokWord = it.miwokWord,
            image = it.image
        )
    }
}

fun List<Miwok>.asDatabaseModel() : List<MiwokDatabaseEntities> {
    return map {
        MiwokDatabaseEntities(
            category = it.category,
            background = it.background,
            defaultWord = it.defaultWord,
            miwokWord = it.miwokWord,
            image = it.image
        )
    }
}