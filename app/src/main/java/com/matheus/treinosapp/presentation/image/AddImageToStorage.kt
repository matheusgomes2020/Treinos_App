package com.matheus.treinosapp.presentation.image

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.matheus.treinosapp.domain.model.Response.Loading
import com.matheus.treinosapp.domain.model.Response.Success
import com.matheus.treinosapp.domain.model.Response.Failure
import com.matheus.treinosapp.presentation.common.ProgressBar

@Composable
fun AddImageToStorage (
    viewModel: ImageViewModel = hiltViewModel(),
    addImageToDatabase: (downloadUrl: Uri) -> Unit
) {
    when (val addImageToStorageResponse = viewModel.addImageUrlToStorageResponse) {
        is Loading -> ProgressBar()
        is Success -> addImageToStorageResponse.data?.let { downloadUrl ->
            LaunchedEffect(downloadUrl) {
                addImageToDatabase(downloadUrl)
            }
        }
        is Failure -> print(addImageToStorageResponse.e)
    }
}