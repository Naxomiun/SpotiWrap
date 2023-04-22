package com.wachon.spotiwrap.core.network.interceptors

import android.os.Looper
import com.wachon.spotiwrap.core.network.exceptions.NetworkOnMainException
import okhttp3.Interceptor
import okhttp3.Response

class ThreadInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if(Looper.getMainLooper().isCurrentThread)
            throw NetworkOnMainException(url = chain.request().url.toString())
        return chain.proceed(chain.request())
    }

}