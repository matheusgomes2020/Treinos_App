package com.matheus.treinosapp.presentation.image

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.matheus.treinosapp.common.Constants
import com.matheus.treinosapp.ui.DpDimensions

@Composable
fun AbrirGaleria (
    openGallery: () -> Unit
) {
    Box(modifier = Modifier
        .padding(DpDimensions.Normal)) {
        Button(onClick = {
            openGallery()
        }) {
            Text(text = "Adicionar IMMM")
        }
    }
}