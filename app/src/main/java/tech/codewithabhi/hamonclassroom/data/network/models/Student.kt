package tech.codewithabhi.hamonclassroom.data.network.models

import com.google.gson.annotations.SerializedName

data class Student(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("age") val age: Int
)