package id.usereal.suitproject.common.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextFieldGeneral(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            onValueChange(newValue)
        },
        placeholder = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(color = Color.Gray, fontSize = 16.sp)
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(15.dp)
            )
            .background(color = Color.White),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
    )


}