package id.usereal.suitproject.common.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardComponent(
    title: String,
    subtitle: String? = null,
    leading: @Composable (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        leading?.invoke()

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontSize = 18.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
            if (subtitle != null) {
                Text(text = subtitle, fontSize = 14.sp, color = androidx.compose.ui.graphics.Color.Gray)
            }
        }

        trailing?.invoke()
    }
}
