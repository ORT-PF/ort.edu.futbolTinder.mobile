package ort.edu.ar.futboltinder.services.APIServices

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    /*override fun intercept(chain: Interceptor.Chain) : Response {
        val request = chain.request().newBuilder()
            .addHeader(
                "Accept", "application/json"
            )
            .addHeader("Authorization", "")
            .build()

            return chain.proceed(request)
    }*/
    override fun intercept(chain: Interceptor.Chain): Response {
        TODO("Not yet implemented")
    }
}