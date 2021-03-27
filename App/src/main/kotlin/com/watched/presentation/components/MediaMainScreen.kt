package com.watched.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.watched.R
import com.watched.presentation.media.model.MediaImageUiModel
import com.watched.presentation.media.model.MediaItemUiModel
import com.watched.presentation.media.model.MediaSectionItemUiModel
import com.watched.presentation.media.model.MediaTopXPosterUiModel
import com.watched.presentation.media.model.MediaTopXUiModel
import com.watched.presentation.theme.WatchedTheme

@Composable
fun MediaMainScreen(
    topXMedia: MediaTopXUiModel?,
    sections: List<MediaSectionItemUiModel>?,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        topXMedia?.let {
            item {
                MediaTopX(it)
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        sections?.let {
            this.items(items = it, itemContent = { item ->
                MediaHorizontalSection(mediaSection = item)
                Spacer(modifier = Modifier.height(8.dp))
            })
        }
    }
}

@Preview
@Composable
private fun MediaMainScreenPreview() {
    val topXMedia = MediaTopXUiModel(
        id = "1",
        poster = MediaTopXPosterUiModel("1", MediaImageUiModel("", "")),
        position = 6
    )
    val sections: List<MediaSectionItemUiModel> = listOf(
        MediaSectionItemUiModel(
            "1", R.string.popular_media,
            listOf(
                MediaItemUiModel("1", MediaImageUiModel("", "")),
                MediaItemUiModel("2", MediaImageUiModel("", "")),
                MediaItemUiModel("3", MediaImageUiModel("", ""))
            )
        ),
        MediaSectionItemUiModel(
            "2", R.string.top_rated_media,
            listOf(
                MediaItemUiModel("1", MediaImageUiModel("", "")),
                MediaItemUiModel("2", MediaImageUiModel("", "")),
                MediaItemUiModel("3", MediaImageUiModel("", ""))
            )
        )
    )
    WatchedTheme {
        Surface {
            MediaMainScreen(topXMedia, sections)
        }
    }
}