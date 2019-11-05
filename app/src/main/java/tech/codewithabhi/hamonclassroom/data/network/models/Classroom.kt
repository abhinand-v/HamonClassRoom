package tech.codewithabhi.hamonclassroom.data.network.models

import com.google.gson.annotations.SerializedName


data class Classroom(
    @SerializedName("id") val id: Int,
    @SerializedName("layout") val layout: String,
    @SerializedName("name") val name: String,
    @SerializedName("size") val size: Int,
    @SerializedName("subject") val subject: String
)