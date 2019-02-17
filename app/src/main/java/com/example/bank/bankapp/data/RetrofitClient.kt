package com.example.bank.bankapp.data

import com.example.bank.bankapp.infra.formatutils.DateTypeDeserializer
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private var ourinstance  : Retrofit? = null
    var token : String = ""

    var okHttpClient = OkHttpClient().newBuilder().readTimeout(15, TimeUnit.SECONDS).addInterceptor(object : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            val originalRequest = chain.request()

            val builder = originalRequest.newBuilder().header("Authorization","$token")

            val newRequest = builder.build()
            return chain.proceed(newRequest)
        }
    }).build()

    val instance: Retrofit
        get(){
            if(ourinstance == null)
            {
                val gson = GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .registerTypeAdapter(Date::class.java, DateTypeDeserializer())
                    .create()

                ourinstance =Retrofit.Builder()
                    .baseUrl("https://bank-app-test.herokuapp.com/api/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }
            return ourinstance!!
        }
}