package ort.edu.ar.futboltinder.services.APIServices

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Url

object RetrofitClientBuilder {
    val baseUrl = "https://625df5ed6c48e8761ba34b95.mockapi.io/"

    fun<T> buildService(service: Class<T>): T{
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .build()
        return retrofit.create(service)
    }

    private fun getClient(): OkHttpClient {
        //return OkHttpClient.Builder().addInterceptor(customInterceptor()).build()
       return OkHttpClient.Builder().build()//
    }
}