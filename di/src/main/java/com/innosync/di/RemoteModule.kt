package com.innosync.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.innosync.data.remote.service.CongressService
import com.innosync.data.remote.service.JobOpeningService
import com.innosync.data.remote.service.JobSearchService
import com.innosync.data.remote.interceptor.LoggingInterceptor
import com.innosync.data.remote.service.AlarmService
import com.innosync.data.remote.service.ChatService
import com.innosync.data.remote.service.LoginService
import com.innosync.data.remote.service.ProfileFixService
import com.innosync.data.remote.service.TokenService
import com.innosync.data.remote.service.UserService
import com.innosync.di.qualifier.BasicOkhttpClient
import com.innosync.di.qualifier.BasicRetrofit
import com.innosync.di.qualifier.TokenOkhttpClient
import com.innosync.di.qualifier.TokenRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.time.LocalDateTime
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeDeserializer())
            .create()
    }

    @Provides
    @Singleton
    fun provideNullSafe(): NullOnEmptyConverterFactory =
        NullOnEmptyConverterFactory()

    @BasicOkhttpClient
    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okhttpBuilder = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            override fun getAcceptedIssuers(): Array<X509Certificate> { return arrayOf() }
        })

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())

        val sslSocketFactory = sslContext.socketFactory

        okhttpBuilder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        okhttpBuilder.hostnameVerifier { hostname, session -> true }
        return okhttpBuilder.build()
    }

    @TokenOkhttpClient
    @Provides
    @Singleton
    fun provideTokenHttpClient(loggingInterceptor: LoggingInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okhttpBuilder = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            override fun getAcceptedIssuers(): Array<X509Certificate> { return arrayOf() }
        })

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())

        val sslSocketFactory = sslContext.socketFactory

        okhttpBuilder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        okhttpBuilder.hostnameVerifier { hostname, session -> true }
        return okhttpBuilder.build()
    }

    @BasicRetrofit
    @Provides
    @Singleton
    fun provideRetrofit(@BasicOkhttpClient okHttpClient: OkHttpClient, gson: Gson, nullSafe :NullOnEmptyConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
//            .addConverterFactory(nullSafe)
            .build()
    }

    @TokenRetrofit
    @Provides
    @Singleton
    fun provideTokenRetrofit(@TokenOkhttpClient okHttpClient: OkHttpClient, gson: Gson, nullSafe :NullOnEmptyConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
//            .addConverterFactory(nullSafe)
            .build()
    }

    @Provides
    @Singleton
    fun provideCongressService(@TokenRetrofit retrofit: Retrofit): CongressService =
        retrofit.create(CongressService::class.java)

    @Provides
    @Singleton
    fun provideJobOpeningService(@TokenRetrofit retrofit: Retrofit): JobOpeningService =
        retrofit.create(JobOpeningService::class.java)

    @Provides
    @Singleton
    fun provideJobSearchService(@TokenRetrofit retrofit: Retrofit): JobSearchService =
        retrofit.create(JobSearchService::class.java)

    @Provides
    @Singleton
    fun provideLoginService(@BasicRetrofit retrofit: Retrofit): LoginService =
        retrofit.create(LoginService::class.java)

    @Provides
    @Singleton
    fun provideTokenService(@BasicRetrofit retrofit: Retrofit): TokenService =
        retrofit.create(TokenService::class.java)

    @Provides
    @Singleton
    fun provideUserService(@TokenRetrofit retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

    @Provides
    @Singleton
    fun provideChatService(@TokenRetrofit retrofit: Retrofit): ChatService =
        retrofit.create(ChatService::class.java)

    @Provides
    @Singleton
    fun provideAlarmService(@TokenRetrofit retrofit: Retrofit): AlarmService =
        retrofit.create(AlarmService::class.java)



    @Provides
    @Singleton
    fun provideProfileFixService(@TokenRetrofit retrofit: Retrofit): ProfileFixService =
        retrofit.create(ProfileFixService::class.java)




}