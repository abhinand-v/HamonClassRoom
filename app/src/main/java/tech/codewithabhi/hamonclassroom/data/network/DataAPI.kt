package tech.codewithabhi.hamonclassroom.data.network

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import tech.codewithabhi.hamonclassroom.BuildConfig
import tech.codewithabhi.hamonclassroom.data.network.responses.StudentListResponse

interface DataAPI {

    @FormUrlEncoded
    @GET("students")
    suspend fun getStudentsList(
        @Field("api_key") apiKey: String = "d2baA"
    ): Response<StudentListResponse>

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