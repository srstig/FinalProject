package com.ray.resqroad.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val carType: String,
    val numberPlate: String,
    val description: String,
    val phone: String,
    val imagePath: String
)
