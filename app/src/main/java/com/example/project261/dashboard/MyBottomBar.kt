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
//kuch nhi ese hi hai
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









/*
================================================================================
  STRICT LINE-BY-LINE EXPLANATION
================================================================================

  ------------------------------------------------------------------------------
  IMPORTS (Lines 1-16)
  ------------------------------------------------------------------------------
  - These lines bring in the necessary tools from Android and Compose libraries.
  - Examples: 'androidx.compose.material3.*' brings in UI components like BottomAppBar.
  - 'androidx.runtime.*' brings in state tools like 'remember' and 'mutableStateOf'.

  ------------------------------------------------------------------------------
  MAIN FUNCTION: MyBottomBar
  ------------------------------------------------------------------------------
  @Composable
  - Annotation telling the compiler: "This function converts data into UI."

  @Preview
  - Annotation telling Android Studio: "Show a preview of this UI in the Design pane."

  fun MyBottomBar() {
  - The start of your function definition.

      val bottomMenuItemsList = prepareBottomMenu()
      - Calls the helper function (defined at the bottom) to get the list of 5 items.
      - Stores this list in 'bottomMenuItemsList'.

      var selectedItem by remember { mutableStateOf("Home") }
      - 'mutableStateOf("Home")': Creates a variable starting with value "Home" that can trigger UI updates.
      - 'remember { ... }': Tells Compose to NOT reset this variable when the screen redraws.
      - 'var selectedItem': The variable name we use to track which tab is active.

      BottomAppBar(
      - The main container for the bottom bar.
          containerColor = colorResource(id = R.color.black3),
          - Sets background color. Uses 'colorResource' to grab a color defined in your XML files.
          tonalElevation = 3.dp
          - Adds a shadow/elevation effect of 3 density-pixels to separate the bar from content.
      ) {
      - Opening curly brace for the content inside the BottomAppBar.

          bottomMenuItemsList.forEach { bottomMenuItem ->
          - A Loop. It goes through every item in your list (Home, Cart, etc.).
          - 'bottomMenuItem': The name we give to the *current* item in the loop.

              NavigationBarItem(
              - The actual clickable button component for a single menu item.

                  selected = selectedItem == bottomMenuItem.label,
                  - LOGIC: Checks if the current global state ('selectedItem') matches
                    THIS button's name ('bottomMenuItem.label').
                  - Returns TRUE if this is the active button, FALSE otherwise.

                  onClick = { selectedItem = bottomMenuItem.label },
                  - EVENT: When the user clicks this button, we update the global
                    'selectedItem' variable to match this button's label.
                  - This change forces the UI to redraw (highlighting this button).

                  icon = {
                  - Defines what the button looks like.
                      Icon(
                          painter = bottomMenuItem.icon,
                          - Loads the specific image (from the data class) for this button.

                          contentDescription = bottomMenuItem.label,
                          - Accessibility text for screen readers (blind users).

                          modifier = Modifier
                              .padding(top = 8.dp)
                              .size(20.dp),
                          - Styling: Adds 8dp space above the icon and forces it to be 20x20dp size.

                          tint = Color.Unspecified
                          - Tells Compose: "Don't color over my image."
                          - Use the original colors of the PNG/Drawable.
                      )
                  },

                  colors = NavigationBarItemDefaults.colors(
                  - Customizing the interaction colors.
                      selectedIconColor = Color.White,
                      - If 'selected' is TRUE: Icon turns White.

                      unselectedIconColor = Color.White.copy(alpha = 0.7f),
                      - If 'selected' is FALSE: Icon is White but 70% transparent (faded).

                      indicatorColor = Color.Transparent
                      - Removes the default pill-shaped background highlight behind the selected icon.
                  )
              )
          } // End of Loop
      } // End of BottomAppBar
  } // End of MyBottomBar function

  ------------------------------------------------------------------------------
  HELPER COMPONENTS
  ------------------------------------------------------------------------------
  data class BottomMenuItem(
      val label: String,
      val icon: Painter
  )
  - A simple container (blueprint) to hold a Name (String) and an Image (Painter).

  @Composable
  fun prepareBottomMenu(): List<BottomMenuItem> {
  - A helper function that returns a List of our data class.
      return listOf(
          BottomMenuItem("Home", painterResource(id = R.drawable.btn_1)),
          - Creates the "Home" item using the image 'btn_1' from resources.
          BottomMenuItem("Cart", painterResource(id = R.drawable.btn_2)),
          ... (Repeats for Favourite, Order, Profile)
      )
  }
================================================================================
*/