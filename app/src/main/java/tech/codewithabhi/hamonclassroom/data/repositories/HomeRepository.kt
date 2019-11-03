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
}