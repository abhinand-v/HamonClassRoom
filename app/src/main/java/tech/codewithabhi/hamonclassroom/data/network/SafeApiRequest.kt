package tech.codewithabhi.hamonclassroom.data.network

import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import tech.codewithabhi.hamonclassroom.util.NetworkConnectionException

abstract class SafeAPIRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {

        val response = call.invoke()

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val message = StringBuilder()
            val error = response.errorBody()?.string()

            error?.let {
                try {
                    message.append(JSONObject(it).getString("msg"))
                } catch (e: JSONException) {
                }
                message.append("\n")
            }
            message.append("Error Code: ${response.code()}")
            throw NetworkConnectionException(message.toString())
        }
    }
}