package com.tanay.githubrepoviewer.data.network.di

import com.squareup.moshi.Moshi
import com.tanay.githubrepoviewer.BuildConfig
import com.tanay.githubrepoviewer.data.network.NetworkAPI
import com.tanay.githubrepoviewer.di.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
object NetworkModule {

    private const val BASE_URL = "https://api.github.com"

    @Provides
    @AppScope
    fun okHttpClient(interceptors: List<@JvmSuppressWildcards Interceptor>): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                interceptors.forEach { addInterceptor(it) }
                readTimeout(45L, TimeUnit.SECONDS)
            }
            .build()
    }

    @Provides
    @AppScope
    fun httpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    @Provides
    fun providerInterceptors(
        loggingInterceptor: HttpLoggingInterceptor
    ): List<Interceptor> {

        return listOf(
            loggingInterceptor
        )
    }


    @Provides
    @AppScope
    fun moshi(): Moshi {
        return Moshi.Builder()
            .build()
    }

    @Provides
    @AppScope
    fun converterFactory(moshi: Moshi): Converter.Factory = MoshiConverterFactory.create(moshi)

    @Provides
    @AppScope
    fun retrofit(factory: Converter.Factory, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(factory)
            .build()


    @Provides
    @AppScope
    fun retrofitBuilder(retrofit: Retrofit): NetworkAPI = retrofit.create(NetworkAPI::class.java)

}