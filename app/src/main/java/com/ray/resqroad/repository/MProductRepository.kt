package com.ray.resqroad.repository

import android.content.Context
import com.ray.resqroad.data.MProductDatabase
import com.ray.resqroad.model.MProduct

class MProductRepository(context: Context) {
    private val mproductDao = MProductDatabase.getDatabase(context).productDao()

    suspend fun insertProduct(MProduct: MProduct) {
        mproductDao.insertMechProduct(MProduct)
    }

    fun getAllMechProducts() = mproductDao.getAllMechProducts()

    suspend fun deleteProduct(MProduct: MProduct) = mproductDao.deleteMechProduct(MProduct)
}