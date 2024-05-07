package com.matheus.treinosapp.domain.repository

import android.net.Uri
import com.matheus.treinosapp.domain.model.Response

typealias AddImageToStorageResponse = Response<Uri>
typealias AddImageUrlToFirestoreResponse = Response<Boolean>
typealias GetImageToFirestoreResponse = Response<String>
interface ImageRepository {
    suspend fun addImageToFirebaseStorage(imageUri: Uri): AddImageToStorageResponse
    suspend fun addImageUrlToFirestore(download: Uri): AddImageUrlToFirestoreResponse
    suspend fun getImageUrlFromFirestore(): GetImageToFirestoreResponse
}