package lec.baekseokuniv.ssiholder.config

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


object RetrofitConfig {
    val issuerBaseUrl = "http://211.37.24.246:8080/"

    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())

    fun <T> createApi(baseUrl: String, api: Class<T>): T = retrofitBuilder
        .baseUrl(baseUrl)
        .build()
        .create(api)
}