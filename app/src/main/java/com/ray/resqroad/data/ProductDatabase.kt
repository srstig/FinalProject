package com.ray.resqroad.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ray.resqroad.data.ProductDao
import com.ray.resqroad.data.UserDao
import com.ray.resqroad.model.MProduct
import com.ray.resqroad.model.Product
import com.ray.resqroad.model.User

@Database(entities = [Product::class, User::class, MProduct :: class], version = 6, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun mproductDao(): MProductDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE:ProductDatabase? = null

        fun getDatabase(context: Context): ProductDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDatabase::class.java,
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