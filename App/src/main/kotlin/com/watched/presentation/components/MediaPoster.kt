package com.watched.presentation.components

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.watched.presentation.media.model.MediaImageUiModel
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun MediaPoster(
    media: MediaImageUiModel,
    modifier: Modifier = Modifier,
    posterSize: MediaImageUiModel.Size = MediaImageUiModel.Size.SMALL
) {
    Card {
        CoilImage(
            data = media.getUrl(posterSize),
            contentDescription = null,
            fadeIn = true,
            modifier = modifier
                .widthIn(120.dp)
                .heightIn(186.dp)
        )
    }
}

@Preview
@Composable
fun MediaPosterPreview() {
    val uiModel = MediaImageUiModel(
        "https://image.tmdb.org/t/p/",
        "/xZ2KER2gOHbuHP2GJoODuXDSZCb.jpg"
    )
    MediaPoster(media = uiModel)
}