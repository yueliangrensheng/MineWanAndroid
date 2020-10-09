package com.yazao.wan.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    version = 1, exportSchema = false,
    entities = [],
)
abstract class WanDatabase : RoomDatabase() {
    abstract fun homeArticleCacheDao(): HomeArticleCacheDao

    companion object {
        fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            WanDatabase::class.java,
            "wan.db"
        ).build()
    }
}
