package id.usereal.suitproject.common.avatar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.transformations
import coil3.transform.CircleCropTransformation
import id.usereal.suitproject.R // Ganti dengan resource yang sesuai di proyekmu

@Composable
fun AvatarUser(
    modifier: Modifier = Modifier,
    imageUrl: String,
    placeholder: Painter = painterResource(id = R.drawable.ic_person), // Ganti dengan ikon person
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .transformations(CircleCropTransformation())
            .build(),
        error = placeholder,
        placeholder = placeholder
    )
    Image(
        painter = painter,
        contentDescription = "User Avatar",
        modifier = modifier.size(48.dp)
    )
}
