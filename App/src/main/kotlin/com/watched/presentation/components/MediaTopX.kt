package com.watched.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.watched.R
import com.watched.presentation.media.model.MediaImageUiModel
import com.watched.presentation.media.model.MediaTopXPosterUiModel
import com.watched.presentation.media.model.MediaTopXUiModel
import com.watched.presentation.theme.WatchedTheme
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun MediaTopX(
    topXMedia: MediaTopXUiModel,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxHeight()
    ) {
        CoilImage(
            data = topXMedia.poster.path,
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            fadeIn = true,
            modifier = modifier
                .heightIn(512.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_throphy),
                contentDescription = null,
                colorFilter = ColorFilter.tint(LocalContentColor.current)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.top_x_today, topXMedia.position),
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun MediaTopXPreview() {
    val imageUiModel = MediaImageUiModel("", "")
    val poster = MediaTopXPosterUiModel("", imageUiModel)
    val uiModel = MediaTopXUiModel("id", poster, 0)
    WatchedTheme(darkTheme = false) {
        Surface {
            MediaTopX(topXMedia = uiModel)
        }
    }
}