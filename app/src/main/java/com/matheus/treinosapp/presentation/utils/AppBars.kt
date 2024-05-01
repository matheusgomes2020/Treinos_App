package com.matheus.treinosapp.presentation.utils

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.matheus.treinosapp.R
import com.matheus.treinosapp.ui.DpDimensions
import com.matheus.treinosapp.ui.theme.TreinosAppTheme

@Composable
fun MainAppBar(
    modifier: Modifier = Modifier,
    onIconClick: () -> Unit = {},
    onLogoClick: () -> Unit = {},
    @DrawableRes icon1: Int,
    title: String,
    isMainScreen: Boolean
) {

    Box(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                horizontal = DpDimensions.Normal,
                vertical = DpDimensions.Small
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.weight),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(DpDimensions.Dp30)
                    .clickable { onLogoClick() }
            )

            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                //color = MaterialTheme.colorScheme.inversePrimary,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = DpDimensions.Normal),
                textAlign = TextAlign.Start,
                maxLines = 1
            )

//            if (!isMainScreen) {
                IconButton(onClick = onIconClick) {
                    Icon(
                        tint = if (isMainScreen) {
                            Color.White
                        } else {Color.Black},
                        painter = painterResource(id = icon1),
                        contentDescription = null,
                    )
                }
            //}
        }
    }
}

@Composable
fun AppBarWithBack(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    title: String,
    backIcon: ImageVector
) {
    Box(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                horizontal = DpDimensions.Smallest,
                vertical = DpDimensions.Small
            )
        ) {

            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = backIcon,
                    contentDescription = null,
                    //tint = MaterialTheme.colorScheme.inversePrimary
                )
            }

            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                //color = MaterialTheme.colorScheme.inversePrimary,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = DpDimensions.Normal),
                textAlign = TextAlign.Start,
            )
        }
    }
}

@Preview
@Composable
fun AppBarWithSearchPreview() {
    TreinosAppTheme {
        AppBarWithBack(
            title = "Aventura",
            backIcon = Icons.Default.ArrowBack
        )
    }
}