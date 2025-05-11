package com.ray.resqroad.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ray.resqroad.data.MProductDatabase
import com.ray.resqroad.model.MProduct
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class MProductViewModel(app: Application) : AndroidViewModel(app) {

    private val context = app.applicationContext
    private val mproductDao = MProductDatabase.getDatabase(app).mproductDao()

    val allMechProducts: LiveData<List<MProduct>> = mproductDao.getAllMechProducts()

    fun addMechProduct(name: String, service: String, location : String, phone: String, imageUri: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val savedImagePath = saveImageToInternalStorage(Uri.parse(imageUri))
            val newMProduct = MProduct(
                name = name,
                service = service,
                location = location,
                phone = phone,
                imagePath = savedImagePath // use saved image path
            )
            mproductDao.insertMechProduct(newMProduct)
        }
    }

    fun updateMechProduct(updatedMProduct: MProduct) {
        viewModelScope.launch(Dispatchers.IO) {
            mproductDao.updateMechProduct(updatedMProduct)
        }
    }

    fun deleteMechProduct(MProduct: MProduct) {
        viewModelScope.launch(Dispatchers.IO) {
            // Delete image from storage
            deleteImageFromInternalStorage(MProduct.imagePath)
            mproductDao.deleteMechProduct(MProduct)
        }
    }

    // Save image permanently to internal storage
    private fun saveImageToInternalStorage(uri: Uri): String {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val fileName = "IMG_${System.currentTimeMillis()}.jpg"
        val file = File(context.filesDir, fileName)

        inputStream?.use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }

        return file.absolutePath
    }

    private fun deleteImageFromInternalStorage(path: String) {
        try {
            val file = File(path)
            if (file.exists()) {
                file.delete()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}