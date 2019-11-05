package tech.codewithabhi.hamonclassroom.data.network

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response
import tech.codewithabhi.hamonclassroom.util.NoInternetException

@Suppress("DEPRECATION")
class NetworkConnectionInterceptor(context: Context) : Interceptor {

    private val applicationContext: Context = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable())
            throw NoInternetException("Check Internet Connection ")
        return chain.proceed(chain.request())
    }

    private fun isInternetAvailable(): Boolean {
        var available = false
        val connManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connManager?.let { manager ->
            manager.activeNetworkInfo?.let { networkInfo ->
                if (networkInfo.isConnected) {
                    available = true
                }
            }
        }
        return available
    }
}