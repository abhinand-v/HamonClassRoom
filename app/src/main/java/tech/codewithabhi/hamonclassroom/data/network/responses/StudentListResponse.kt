package tech.codewithabhi.hamonclassroom.data.network.responses

import tech.codewithabhi.hamonclassroom.data.network.models.Student

data class StudentListResponse(
    val students: List<Student>
)