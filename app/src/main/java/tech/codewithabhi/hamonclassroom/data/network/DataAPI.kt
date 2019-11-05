package tech.codewithabhi.hamonclassroom.data.network

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import tech.codewithabhi.hamonclassroom.BuildConfig
import tech.codewithabhi.hamonclassroom.data.network.responses.StudentListResponse
import tech.codewithabhi.hamonclassroom.data.network.responses.SubjectListResponse

interface DataAPI {

    @GET("students/?")
    suspend fun getStudentsList(
        @Query("api_key") api_key: String = "d2baA"
    ): Response<StudentListResponse>

    @GET("subjects/?")
    suspend fun getSubjectList(
        @Query("api_key") api_key: String = "d2baA"
    ): Response<SubjectListResponse>

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