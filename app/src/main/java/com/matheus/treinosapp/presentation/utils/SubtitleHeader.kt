package com.matheus.treinosapp.presentation.utils

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.matheus.treinosapp.R
import com.matheus.treinosapp.ui.DpDimensions
import com.matheus.treinosapp.ui.theme.TreinosAppTheme

@Composable
fun SubtitleHeader(
    modifier: Modifier = Modifier,
    title: String,
    isIconVisible: Boolean,
    isSystemInDarkTheme: Boolean,
    onClick: () -> Unit = {}
) {


    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            //fontWeight = FontWeight.SemiBold,
            fontSize = 22.sp,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black,
            modifier = Modifier.weight(1f)
        )


        IconButton(onClick = onClick) {
            Icon(
                painter = painterResource(id = R.drawable.right_arrow),
                contentDescription = "Right arrow",
                //tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(DpDimensions.Dp20)
                    .alpha(if (!isIconVisible) 0f else 1f),


            )
        }

    }

}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CategoryHeaderPreview() {
    TreinosAppTheme {
        SubtitleHeader(title = "Em alta", modifier = Modifier.fillMaxWidth(), isIconVisible = true, isSystemInDarkTheme = true)
    }
}