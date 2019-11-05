package tech.codewithabhi.hamonclassroom.ui.home.subject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tech.codewithabhi.hamonclassroom.data.repositories.HomeRepository

@Suppress("UNCHECKED_CAST")
class SubjectViewModelFactory(
    private val homeRepository: HomeRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SubjectViewModel(homeRepository) as T
    }
}