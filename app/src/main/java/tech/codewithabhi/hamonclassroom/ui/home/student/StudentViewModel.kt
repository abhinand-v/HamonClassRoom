package tech.codewithabhi.hamonclassroom.ui.home.student

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import tech.codewithabhi.hamonclassroom.data.network.models.Student
import tech.codewithabhi.hamonclassroom.data.repositories.HomeRepository
import tech.codewithabhi.hamonclassroom.util.ApiExceptions
import tech.codewithabhi.hamonclassroom.util.NetworkConnectionException

class StudentViewModel(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private lateinit var job: CompletableJob

    fun getStudentList(): LiveData<List<Student>> {
        val liveList = MutableLiveData<List<Student>>()
        CoroutineScope(Job() + Dispatchers.Default).launch {
            try {
                val response = homeRepository.getStudentList()
                liveList.value = response.students
            } catch (e: ApiExceptions) {

            } catch (e: NetworkConnectionException) {

            }
        }
        return liveList
    }

}
