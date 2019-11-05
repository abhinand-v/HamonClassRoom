package tech.codewithabhi.hamonclassroom.data.network.responses

import com.google.gson.annotations.SerializedName
import tech.codewithabhi.hamonclassroom.data.network.models.Classroom

data class ClassroomListResponse(
    @SerializedName("classrooms") val classrooms: List<Classroom>
)