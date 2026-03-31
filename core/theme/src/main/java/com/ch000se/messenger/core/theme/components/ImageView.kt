package com.ch000se.messenger.core.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.ch000se.messenger.core.essentials.entities.ImageSource
import com.ch000se.messenger.core.theme.Shapes
import com.ch000se.theme.R

@Composable
fun ImageView(
    imageSource: ImageSource,
    modifier: Modifier,
    contentDescription: String? = null
) {
    when (imageSource) {
        ImageSource.Empty -> EmptyImageView(modifier, contentDescription)

        is ImageSource.Remote -> RemoteImageView(imageSource.url, modifier, contentDescription)
    }
}

@Composable
private fun EmptyImageView(
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    Image(
        painter = painterResource(R.drawable.ic_empty_image),
        contentDescription = contentDescription,
        modifier = modifier.background(
            color = MaterialTheme.colorScheme.secondaryContainer,
            shape = Shapes.MediumRoundedCornerShape
        )
    )
}

@Composable
private fun RemoteImageView(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    SubcomposeAsyncImage(
        model = url,
        modifier = modifier,
        contentDescription = contentDescription,
        error = {
            EmptyImageView(Modifier.matchParentSize(), contentDescription)
        }
    )
}

@Preview
@Composable
private fun EmptyImageViewPreview() {
    ImageView(
        imageSource = ImageSource.Empty,
        modifier = Modifier.size(100.dp)
    )
}