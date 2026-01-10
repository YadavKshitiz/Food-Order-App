package com.example.project261.dashboard

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project261.R

@Composable
@Preview
fun MyBottomBar() {

    val bottomMenuItemsList = prepareBottomMenu()
    var selectedItem by remember { mutableStateOf("Home") }

    BottomAppBar(
        containerColor = colorResource(id = R.color.black3),
        tonalElevation = 3.dp
    ) {

        bottomMenuItemsList.forEach { bottomMenuItem ->

            NavigationBarItem(
                selected = selectedItem == bottomMenuItem.label,
                onClick = { selectedItem = bottomMenuItem.label },

                icon = {
                    Icon(
                        painter = bottomMenuItem.icon,
                        contentDescription = bottomMenuItem.label,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .size(20.dp),
                        tint = Color.Unspecified
                    )
                },

                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.White.copy(alpha = 0.7f),
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

data class BottomMenuItem(
    val label: String,
    val icon: Painter
)

@Composable
fun prepareBottomMenu(): List<BottomMenuItem> {
    return listOf(
        BottomMenuItem("Home", painterResource(id = R.drawable.btn_1)),
        BottomMenuItem("Cart", painterResource(id = R.drawable.btn_2)),
        BottomMenuItem("Favourite", painterResource(id = R.drawable.btn_3)),
        BottomMenuItem("Order", painterResource(id = R.drawable.btn_4)),
        BottomMenuItem("Profile", painterResource(id = R.drawable.btn_5))
    )
}
