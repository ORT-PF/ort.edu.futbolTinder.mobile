package ort.edu.ar.futboltinder.services.APIServices

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Url

object RetrofitClientBuilder {
    val baseUrl = "https://localhost:5001/"

    fun<T> buildService(service: Class<T>, url: String): T{
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl.plus(url)) // change this IP for testing by your actual machine IP
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(service)
    }
}