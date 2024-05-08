package com.matheus.treinosapp.presentation.image

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheus.treinosapp.domain.model.Response
import com.matheus.treinosapp.domain.model.Response.Success
import com.matheus.treinosapp.domain.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val repo: ImageRepository
) : ViewModel(){
    var addImageUrlToStorageResponse by mutableStateOf<Response<Uri>>(Success(null))
    private set

    var addImageDatabaseResponse by mutableStateOf<Response<Boolean>>(Success(null))
        private set

    var getImageFromDatabaseResponse by mutableStateOf<Response<String>>(Success(null))
        private set

    fun addImageToStorage(imageUri: Uri, exerciseId: String) = viewModelScope.launch {
        addImageUrlToStorageResponse = Response.Loading
        addImageUrlToStorageResponse = repo.addImageToFirebaseStorage(imageUri, exerciseId)
    }

    fun addImageToDatabase(downloadUrl: Uri) = viewModelScope.launch {
        addImageDatabaseResponse = Response.Loading
        addImageDatabaseResponse = repo.addImageUrlToFirestore(downloadUrl)
    }

    fun getImageFromDatabase() = viewModelScope.launch {
        getImageFromDatabaseResponse = Response.Loading
        getImageFromDatabaseResponse = repo.getImageUrlFromFirestore()
    }

}