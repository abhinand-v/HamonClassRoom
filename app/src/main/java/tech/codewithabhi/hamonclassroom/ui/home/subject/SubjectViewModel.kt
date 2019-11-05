package tech.codewithabhi.hamonclassroom.ui.home.subject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import tech.codewithabhi.hamonclassroom.data.network.models.Subject
import tech.codewithabhi.hamonclassroom.data.repositories.HomeRepository
import tech.codewithabhi.hamonclassroom.util.ApiExceptions
import tech.codewithabhi.hamonclassroom.util.NetworkConnectionException

class SubjectViewModel(
    private val homeRepository: HomeRepository
) : ViewModel() {

    lateinit var listener: SubjectFragmentListener

    fun getList(): LiveData<List<Subject>> {
        val liveList = MutableLiveData<List<Subject>>()
        CoroutineScope(Job() + Dispatchers.Main).launch {
            listener.startStopRefresh(true)
            try {
                val response = homeRepository.getSubjectList()
                liveList.value = response.subjects
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
