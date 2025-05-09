package com.ray.resqroad.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mechproducts")
data class MProduct(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val price: Double,
    val phone: String,
    val imagePath: String
)
