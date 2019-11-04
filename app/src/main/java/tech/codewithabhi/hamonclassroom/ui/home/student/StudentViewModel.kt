package tech.codewithabhi.hamonclassroom.ui.home.student

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import tech.codewithabhi.hamonclassroom.data.network.models.Student
import tech.codewithabhi.hamonclassroom.data.repositories.HomeRepository
import tech.codewithabhi.hamonclassroom.util.ApiExceptions
import tech.codewithabhi.hamonclassroom.util.NetworkConnectionException

class StudentViewModel(
    private val homeRepository: HomeRepository
) : ViewModel() {

    lateinit var listener: StudentFragmentListener

    fun getStudentList(): LiveData<List<Student>> {
        val liveList = MutableLiveData<List<Student>>()
        CoroutineScope(Job() + Dispatchers.Main).launch {
            listener.startStopRefresh(true)
            try {
                val response = homeRepository.getStudentList()
                liveList.value = response.students
                listener.startStopRefresh(false)
            } catch (e: ApiExceptions) {
                listener.startStopRefresh(false)
            } catch (e: NetworkConnectionException) {
                listener.startStopRefresh(false)
            }
        }
        return liveList
    }

}
