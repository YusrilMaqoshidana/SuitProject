package id.usereal.suitproject.common.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.usereal.suitproject.ui.theme.primary

@Composable
fun LongButton(
    text: String,
    onClick: () -> Unit,
    buttonColors: ButtonColors =
        ButtonDefaults.buttonColors(
            containerColor = primary,
            contentColor = Color.White,
        ),
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier =
        modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .height(50.dp),
        colors = buttonColors,
        shape = Shapes().small,
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}