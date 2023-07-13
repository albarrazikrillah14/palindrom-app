package com.medomeckz.palindromapp.data.remote


import com.medomeckz.palindromapp.utils.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiClient {
    private val okHttp = OkHttpClient.Builder()
        .apply {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(loggingInterceptor)
        }.build()

    private val retrofit = Retrofit.Builder()
        .client(okHttp)
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val appService = retrofit.create<AppService>()
}