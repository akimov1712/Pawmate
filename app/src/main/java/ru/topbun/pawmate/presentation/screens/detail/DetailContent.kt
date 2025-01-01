package ru.topbun.pawmate.presentation.screens.detail

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontVariation.width
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.topbun.pawmate.R
import ru.topbun.pawmate.entity.Pet
import ru.topbun.pawmate.entity.PetType
import ru.topbun.pawmate.entity.Tip
import ru.topbun.pawmate.presentation.theme.Colors
import ru.topbun.pawmate.presentation.theme.Fonts
import ru.topbun.pawmate.presentation.theme.Typography
import ru.topbun.pawmate.presentation.theme.components.AppIcon
import ru.topbun.pawmate.repository.TipRepository

data class DetailScreen(
    val pet: Pet
): Screen {

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            val context = LocalContext.current
            val navigator = LocalNavigator.currentOrThrow
            var tips by remember {
                mutableStateOf<List<Tip>>(emptyList())
            }
            val repository = TipRepository(context)
            LaunchedEffect(Unit) {
                tips = repository.getTipList(PetType.DOG)
            }
            Header(
                onClickEdit = {},
                onClickDelete = {},
            )
            Spacer(modifier = Modifier.height(20.dp))
            Image(pet.image)
            Spacer(modifier = Modifier.height(20.dp))
            Fields()
            Spacer(modifier = Modifier.height(20.dp))
            Tips(tips)
        }
    }

}

@Composable
private fun Tips(tips: List<Tip>) {
    Column {
        Text(
            text = "Советы:",
            style = Typography.APP_TEXT,
            color = Colors.BLACK,
            fontSize = 24.sp,
            fontFamily = Fonts.SF.SEMI_BOLD
        )
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            tips.forEachIndexed { index, it ->
                Row {
                    Text(
                        text = (index + 1).toString(),
                        style = Typography.APP_TEXT,
                        color = Colors.BLACK,
                        fontSize = 18.sp,
                        fontFamily = Fonts.SF.BOLD
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = it.text,
                        style = Typography.APP_TEXT,
                        color = Colors.BLACK,
                        fontSize = 16.sp,
                        fontFamily = Fonts.SF.MEDIUM
                    )
                }
            }
        }
    }
}

@Composable
private fun Fields() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TitleWithTextField(
            title = "Кличка:",
            value = "Константин",
            enabled = true,
            keyboardType = KeyboardType.Text,
            onChangeValue = {}
        )
        TitleWithTextField(
            title = "Возраст:",
            value = "2 года",
            enabled = true,
            keyboardType = KeyboardType.Number,
            onChangeValue = {}
        )

        TitleWithTextField(
            title = "Порода:",
            value = "Корги",
            enabled = true,
            keyboardType = KeyboardType.Text,
            onChangeValue = {}
        )
        TitleWithTextField(
            title = "Тип:",
            value = "Собака",
            enabled = true,
            keyboardType = KeyboardType.Text,
            onChangeValue = {}
        )
    }
}

@Composable
private fun TitleWithTextField(
    title: String,
    value: String,
    enabled: Boolean,
    keyboardType: KeyboardType,
    onChangeValue: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = title,
            style = Typography.APP_TEXT,
            color = Colors.BLACK,
            fontSize = 20.sp,
            fontFamily = Fonts.SF.SEMI_BOLD
        )
        Spacer(modifier = Modifier.width(4.dp))
        BasicTextField(
            value = value,
            onValueChange = onChangeValue,
            enabled = enabled,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine = true,
            textStyle = TextStyle(
                color = Colors.BROWN,
                fontSize = 20.sp,
                fontFamily = Fonts.SF.SEMI_BOLD
            )
        )
    }
}

@Composable
private fun Image(image: String?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .border(1.5.dp, Colors.BLUE_GRAY, RoundedCornerShape(16.dp))
    ) {
        val bitmap = BitmapFactory.decodeFile(image)
        bitmap?.let {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp)),
                bitmap = it.asImageBitmap(),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun Header(onClickEdit: () -> Unit, onClickDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        val navigator = LocalNavigator.currentOrThrow
        AppIcon(R.drawable.ic_back) {
            navigator.pop()
        }
        Spacer(modifier = Modifier.weight(1f))
        AppIcon(R.drawable.ic_edit) {
            onClickEdit()
        }
        AppIcon(R.drawable.ic_delete) {
            onClickDelete()
        }
    }
}