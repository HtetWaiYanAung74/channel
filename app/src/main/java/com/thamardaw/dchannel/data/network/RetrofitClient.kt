package com.thamardaw.dchannel.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.thamardaw.dchannel.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}

val moshi = Moshi.Builder()
    .add(NullToEmptyStringAdapter)
    .add(KotlinJsonAdapterFactory())
    .build()

val dchannelApis: ApiService by lazy {
    retrofit.create(ApiService::class.java)
}


fun okHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(httpLoggingInterceptor)
    }
    return builder.build()
}