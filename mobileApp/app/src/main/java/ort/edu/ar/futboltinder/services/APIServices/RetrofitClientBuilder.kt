package ort.edu.ar.futboltinder.services.APIServices

import okhttp3.OkHttpClient
import ort.edu.ar.futboltinder.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Url
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object RetrofitClientBuilder {
    val baseUrl = "https://192.168.0.155:5001" //Replace here with your local ip address. Make sure your local api is running on that ip

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
       return OkHttpClient.Builder().apply{
           if(BuildConfig.DEBUG){
               ignoreAllSSLErrors()
           }
       }.build()//
    }

    private fun OkHttpClient.Builder.ignoreAllSSLErrors(): OkHttpClient.Builder {
        val naiveTrustManager = object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
            override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) = Unit
            override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) = Unit
        }

        val insecureSocketFactory = SSLContext.getInstance("TLSv1.2").apply {
            val trustAllCerts = arrayOf<TrustManager>(naiveTrustManager)
            init(null, trustAllCerts, SecureRandom())
        }.socketFactory

        sslSocketFactory(insecureSocketFactory, naiveTrustManager)
        hostnameVerifier(HostnameVerifier { _, _ -> true })
        return this
    }
}