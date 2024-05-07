package com.matheus.treinosapp.data.repository

import android.net.Uri
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.matheus.treinosapp.common.Constants.CREATED_AT
import com.matheus.treinosapp.common.Constants.IMAGES
import com.matheus.treinosapp.common.Constants.IMAGE_NAME
import com.matheus.treinosapp.common.Constants.UID
import com.matheus.treinosapp.common.Constants.URL
import com.matheus.treinosapp.domain.model.Response
import com.matheus.treinosapp.domain.repository.AddImageToStorageResponse
import com.matheus.treinosapp.domain.repository.AddImageUrlToFirestoreResponse
import com.matheus.treinosapp.domain.repository.GetImageToFirestoreResponse
import com.matheus.treinosapp.domain.repository.ImageRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val storage: FirebaseStorage,
    private val db: FirebaseFirestore
): ImageRepository {
    override suspend fun addImageToFirebaseStorage(imageUri: Uri): AddImageToStorageResponse {
        return try {
            val downloadUrl = storage.reference.child(IMAGES).child(IMAGE_NAME)
                .putFile(imageUri).await()
                .storage.downloadUrl.await()
            Response.Success(downloadUrl)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun addImageUrlToFirestore(download: Uri): AddImageUrlToFirestoreResponse {
        return try {
            db.collection(IMAGES).document(UID).set(mapOf(
                URL to URL,
                CREATED_AT to FieldValue.serverTimestamp()
            )).await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun getImageUrlFromFirestore(): GetImageToFirestoreResponse {
        return try {
            val imageUrl = db.collection(IMAGES).document(UID).get().await().getString(URL)
            Response.Success(imageUrl)
        } catch (e:Exception) {
            Response.Failure(e)
        }
    }

}