package com.desafiomobile.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.desafiomobile.model.dao.PatApelidoDao
import com.desafiomobile.model.entity.PatApelido

@Database(entities = [PatApelido::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun patApelidoDao(): PatApelidoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "pat_apelido_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}