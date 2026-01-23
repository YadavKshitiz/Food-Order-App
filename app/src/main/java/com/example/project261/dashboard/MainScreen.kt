package com.example.project261.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.project261.viewModel.MainViewModel
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.project261.R
import com.example.project261.domain.CategoryModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onOpenItems: (id: String, title: String) -> Unit,

    ) {
    val snackbarHostState = remember { SnackbarHostState() }
    val categories = remember { mutableStateListOf<CategoryModel>() }
    var showCategoryLoading by remember{mutableStateOf(true)}



    Scaffold(
        bottomBar = { MyBottomBar() },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(colorResource(id = R.color.black2)),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item(span = { GridItemSpan(2) }) {
                TopBar()
            }
                item(span = { GridItemSpan(2) }) {
                    CategorySection(
                        categories = categories,
                        showCategoryLoading=showCategoryLoading,
                        onCategoryClick = {cat->onOpenItems(cat.Id.toString(),cat.Name)}
                    )
                }
        }
    }
}