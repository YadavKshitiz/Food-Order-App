package com.example.project261.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project261.R

@Composable
@Preview
fun TopBar() {

    var text by rememberSaveable { mutableStateOf("") }

    Row(
        modifier = Modifier
            .padding(top = 48.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {

        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile",
            modifier = Modifier
                .size(45.dp)
                .clickable {}
        )

        TextField(
            value = text,
            onValueChange = { text = it },

            label = {
                Text(
                    text = "What would you like to eat?",
                    fontSize = 13.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            },

            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "Search",
                    modifier = Modifier.size(20.dp)
                )
            },

            shape = RoundedCornerShape(25.dp),

            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorResource(id = R.color.black3),
                unfocusedContainerColor = colorResource(id = R.color.black3),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedLabelColor = Color.Transparent,
                unfocusedLabelColor = Color.Transparent
            ),

            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp)
                .height(45.dp)
                .background(color = colorResource(id = R.color.black3), CircleShape)
        )
        Image(
            painter = painterResource(id = R.drawable.bell_icon),
            contentDescription = "Search",
            modifier = Modifier
                .clickable {}
                .size(20.dp)
        )

    }
}
