package tech.codewithabhi.hamonclassroom.util

import java.io.IOException
import java.net.ConnectException

class ApiExceptions(message: String) : IOException(message)
class NetworkConnectionException(message: String) : ConnectException(message)
class NoInternetException(message: String) : IOException(message)