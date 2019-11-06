package tech.codewithabhi.hamonclassroom.data.network

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import tech.codewithabhi.hamonclassroom.BuildConfig
import tech.codewithabhi.hamonclassroom.data.network.models.Classroom
import tech.codewithabhi.hamonclassroom.data.network.models.Student
import tech.codewithabhi.hamonclassroom.data.network.models.Subject
import tech.codewithabhi.hamonclassroom.data.network.responses.ClassroomListResponse
import tech.codewithabhi.hamonclassroom.data.network.responses.StudentListResponse
import tech.codewithabhi.hamonclassroom.data.network.responses.SubjectListResponse

interface DataAPI {

    @GET("students/")
    suspend fun getStudentsList(
        @Query("api_key") api_key: String = "d2baA"
    ): Response<StudentListResponse>

    @GET("students/{id}?")
    suspend fun getStudentsDetails(
        @Path("id") id: Int,
        @Query("api_key") api_key: String = "d2baA"
    ): Response<Student>

    @GET("subjects/?")
    suspend fun getSubjectList(
        @Query("api_key") api_key: String = "d2baA"
    ): Response<SubjectListResponse>

    @GET("subjects/{id}?")
    suspend fun getSubjectDetails(
        @Path("id") id: Int,
        @Query("api_key") api_key: String = "d2baA"
    ): Response<Subject>

    @GET("classrooms/?")
    suspend fun getClassroomtList(
        @Query("api_key") api_key: String = "d2baA"
    ): Response<ClassroomListResponse>

    @GET("classrooms/{id}?")
    suspend fun getClassroomsDetails(
        @Path("id") id: Int,
        @Query("api_key") api_key: String = "d2baA"
    ): Response<Classroom>

    @FormUrlEncoded
    @PATCH("classrooms/{id}?")
    suspend fun assignClassroomToSubject(
        @Path("id") ClassroomId: Int,
        @Field("subject") SubjectId: Int,
        @Query("api_key") api_key: String = "d2baA"
    ): Response<Classroom>

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): DataAPI {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)

                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DataAPI::class.java)
        }
    }
}