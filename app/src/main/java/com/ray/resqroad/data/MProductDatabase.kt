package com.ray.resqroad.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ray.resqroad.model.MProduct
import com.ray.resqroad.model.User

@Database(entities = [MProduct::class, User::class], version = 3, exportSchema = false)
abstract class MProductDatabase : RoomDatabase() {
    abstract fun productDao(): MProductDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE:MProductDatabase? = null

        fun getDatabase(context: Context): MProductDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MProductDatabase::class.java,
                    "main_database"
                )
                    .fallbackToDestructiveMigration() // 💥 This clears DB on version change
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}