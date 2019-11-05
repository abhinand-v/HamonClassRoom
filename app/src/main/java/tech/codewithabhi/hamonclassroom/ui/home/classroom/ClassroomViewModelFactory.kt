package tech.codewithabhi.hamonclassroom.ui.home.classroom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tech.codewithabhi.hamonclassroom.data.repositories.HomeRepository

@Suppress("UNCHECKED_CAST")
class ClassroomViewModelFactory(
    private val homeRepository: HomeRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ClassroomViewModel(homeRepository) as T
    }
}