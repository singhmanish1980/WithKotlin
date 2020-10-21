package com.example.myapplication

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.*

class BaseRequestInterceptor(private val requestHeaderValues: HashMap<String, String>?) :
    Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        /** Will be called for every request. chain will include the request data, call chain.proceed() to continue with the HTTP request. */
        val original = chain.request()
        val newBuilder = original.newBuilder()
        if (requestHeaderValues != null && requestHeaderValues.size > 0) {
            val keys: Set<String> = requestHeaderValues.keys
            for (key in keys) {
                newBuilder.addHeader(key, requestHeaderValues[key])
            }
            requestHeaderValues.clear()
        }
        // Customize the request
        val request = newBuilder //TODO check needed default headers
            .header(MainConstants.CONTENT_TYPE, MainConstants.APPLICATION_JSON)
            .header(MainConstants.ACCEPT, MainConstants.APPLICATION_JSON)
            .header(MainConstants.CACHE_CONTROL, MainConstants.NO_STORE)
            .method(original.method(), original.body())
            .build()
        // Customize or return the response
        return chain.proceed(request)
    }

    companion object {
        private val TAG = BaseRequestInterceptor::class.java.name
    }

}