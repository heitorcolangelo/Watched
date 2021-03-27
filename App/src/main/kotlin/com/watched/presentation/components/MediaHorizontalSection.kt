package com.watched.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.watched.R
import com.watched.presentation.media.model.MediaImageUiModel
import com.watched.presentation.media.model.MediaItemUiModel
import com.watched.presentation.media.model.MediaSectionItemUiModel
import com.watched.presentation.theme.WatchedTheme

@Composable
fun MediaHorizontalSection(
    mediaSection: MediaSectionItemUiModel,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(mediaSection.title),
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow {
            item {
                Spacer(modifier = Modifier.width(16.dp))
            }
            val mediaItems: List<MediaItemUiModel> = mediaSection.list
            this.itemsIndexed(items = mediaItems, itemContent = { index, item ->
                MediaPoster(media = item.poster)
                if (index != mediaItems.size - 1) {
                    Spacer(modifier = Modifier.width(8.dp))
                }
            })
            item {
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}

@Preview
@Composable
private fun HorizontalSectionPreview() {
    val media = MediaSectionItemUiModel(
        id = "",
        title = R.string.popular_media,
        list = listOf(
            MediaItemUiModel("1", MediaImageUiModel("", "")),
            MediaItemUiModel("2", MediaImageUiModel("", "")),
            MediaItemUiModel("3", MediaImageUiModel("", ""))
        )
    )
    WatchedTheme(darkTheme = true) {
        Surface {
            MediaHorizontalSection(mediaSection = media)
        }
    }
}