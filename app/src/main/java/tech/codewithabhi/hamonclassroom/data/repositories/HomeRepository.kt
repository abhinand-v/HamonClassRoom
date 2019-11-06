package tech.codewithabhi.hamonclassroom.data.repositories

import tech.codewithabhi.hamonclassroom.data.network.DataAPI
import tech.codewithabhi.hamonclassroom.data.network.SafeAPIRequest

class HomeRepository(
    private val api: DataAPI
) : SafeAPIRequest() {
    // get student list
    suspend fun getStudentList() = apiRequest {
        api.getStudentsList()
    }

    suspend fun getSubjectList() = apiRequest {
        api.getSubjectList()
    }

    suspend fun getSubjectDetails(id: Int) = apiRequest {
        api.getSubjectDetails(id)
    }

    suspend fun getClassroomList() = apiRequest {
        api.getClassroomtList()
    }

    suspend fun getClassroomDetails(id: Int) = apiRequest {
        api.getClassroomsDetails(id)
    }

    suspend fun assignSubjectToClassroom(classroomId: Int, subjectId: Int) = apiRequest {
        api.assignClassroomToSubject(classroomId, subjectId)
    }
}