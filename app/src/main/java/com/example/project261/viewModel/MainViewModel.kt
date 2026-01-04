package com.example.project261.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.project261.Repository.MainRepository
import com.example.project261.domain.CategoryModel

class MainViewModel: ViewModel() {
    private val repository= MainRepository()

    fun loadCategory(): LiveData<List<CategoryModel>> {
        return repository.loadCategory()
    }
}