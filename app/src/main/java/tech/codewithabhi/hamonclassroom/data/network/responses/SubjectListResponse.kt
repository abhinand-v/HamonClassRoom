package tech.codewithabhi.hamonclassroom.data.network.responses

import com.google.gson.annotations.SerializedName
import tech.codewithabhi.hamonclassroom.data.network.models.Subject

data class SubjectListResponse(
    @SerializedName("subjects") val subjects: List<Subject>
)