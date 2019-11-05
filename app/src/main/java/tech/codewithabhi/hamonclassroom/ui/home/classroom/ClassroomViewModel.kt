package tech.codewithabhi.hamonclassroom.ui.home.classroom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import tech.codewithabhi.hamonclassroom.data.network.models.Classroom
import tech.codewithabhi.hamonclassroom.data.repositories.HomeRepository
import tech.codewithabhi.hamonclassroom.util.ApiExceptions
import tech.codewithabhi.hamonclassroom.util.NetworkConnectionException

class ClassroomViewModel(
    private val repository: HomeRepository
) : ViewModel() {

    lateinit var listener: ClassroomFragmentListener

    fun getList(): LiveData<List<Classroom>> {
        val liveList = MutableLiveData<List<Classroom>>()
        CoroutineScope(Job() + Dispatchers.Main).launch {
            listener.startStopRefresh(true)
            try {
                val response = repository.getClassroomList()
                liveList.value = response.classrooms
                listener.startStopRefresh(false)
            } catch (e: ApiExceptions) {
                listener.startStopRefresh(false)
            } catch (e: NetworkConnectionException) {
                listener.startStopRefresh(false)
            }
        }
        return liveList
    }

    fun getSubjectRawList(): LiveData<List<String>> {
        val liveList = MutableLiveData<List<String>>()
        CoroutineScope(Job() + Dispatchers.Main).launch {
            listener.startStopRefresh(true)
            try {
                val response = repository.getSubjectList()
                val originalSubjects = response.subjects
                val rawSubjects = originalSubjects.map {
                    it.name
                }
                liveList.postValue(rawSubjects)
                listener.startStopRefresh(false)
            } catch (e: ApiExceptions) {
                listener.startStopRefresh(false)
            } catch (e: NetworkConnectionException) {
                listener.startStopRefresh(false)
            }
        }
        return liveList
    }

    fun getClassroomDetails(classroom: Classroom): LiveData<Classroom> {
        val liveList = MutableLiveData<Classroom>()
        CoroutineScope(Job() + Dispatchers.Main).launch {
            listener.startStopRefresh(true)
            try {
                val response = repository.getClassroomDetails(classroom.id)
                val subject = if (response.subject.isNotEmpty()) {
                    repository.getSubjectDetails(response.subject.toInt()).name
                } else {
                    ""
                }
                val tClassroom = Classroom(
                    classroom.id,
                    classroom.layout,
                    classroom.name,
                    classroom.size,
                    subject
                )
                liveList.value = tClassroom
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