package tech.codewithabhi.hamonclassroom.data.network.models

import com.google.gson.annotations.SerializedName

data class Subject(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("credits") val credits: Int,
    @SerializedName("teacher") val teacher: String
)