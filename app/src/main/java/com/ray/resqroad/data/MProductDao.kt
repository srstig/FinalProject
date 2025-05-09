package com.ray.resqroad.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ray.resqroad.model.MProduct

@Dao
interface MProductDao {
    @Query("SELECT * FROM mechproducts")
    fun getAllMechProducts(): LiveData<List<MProduct>>

    @Insert
    suspend fun insertMechProduct(MProduct: MProduct)

    @Update
    suspend fun updateMechProduct(Product: MProduct)

    @Delete
    suspend fun deleteMechProduct(MProduct: MProduct)
}