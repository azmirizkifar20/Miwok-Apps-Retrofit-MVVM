@file:Suppress("SpellCheckingInspection")

package org.d3if4055.miwokapps.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.d3if4055.miwokapps.model.Miwok
import org.d3if4055.miwokapps.model.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class MiwokDatabaseModel constructor(
    val category: String,
    val background: String,
    val defaultWord: String,
    @PrimaryKey
    val miwokWord: String,
    val image: String = ""
)

//fun List<MiwokDatabaseModel>.asDomainModel(): List<Miwok> {
//    return map {
//        Miwok(
//            category = it.category,
//            background = it.background,
//            defaultWord = it.defaultWord,
//            miwokWord = it.miwokWord,
//            image = it.image
//        )
//    }
//}

fun convertToDomainModel(data: List<MiwokDatabaseModel>): List<Miwok> {
    return data.map {
        Miwok(
            category = it.category,
            background = it.background,
            defaultWord = it.defaultWord,
            miwokWord = it.miwokWord,
            image = it.image
        )
    }
}

//fun List<Miwok>.asDatabaseModel(): List<MiwokDatabaseModel> {
//    return map {
//        MiwokDatabaseModel (
//            category = it.category,
//            background = it.background,
//            defaultWord = it.defaultWord,
//            miwokWord = it.miwokWord,
//            image = it.image
//        )
//    }
//}

fun convertToDatabaseModel(data: List<Miwok>): List<MiwokDatabaseModel> {
    return data.map {
        MiwokDatabaseModel(
            category = it.category,
            background = it.background,
            defaultWord = it.defaultWord,
            miwokWord = it.miwokWord,
            image = it.image
        )
    }
}