package lec.baekseokuniv.ssiholder.config

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


object RetrofitConfig {
    val issuerBaseUrl = "http://211.37.24.246:8080/"

    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))

    fun <T> createApi(baseUrl: String, api: Class<T>): T = retrofitBuilder
        .baseUrl(baseUrl)
        .build()
        .create(api)
}