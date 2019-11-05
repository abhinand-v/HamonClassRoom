package tech.codewithabhi.hamonclassroom.data.network.responses

import com.google.gson.annotations.SerializedName
import tech.codewithabhi.hamonclassroom.data.network.models.Student

data class StudentListResponse(
    @SerializedName("students") val students: List<Student>
)