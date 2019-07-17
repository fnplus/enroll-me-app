package tech.fnplus.enrollme.data.source.remote

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import tech.fnplus.enrollme.data.Event


interface APIServices {

    @GET("geek-meetup-chennai")
    fun loadEvents(): Call<List<Event>>
}

object RetrofitClient {
    private var retrofit: Retrofit? = null


    fun getClient(baseUrl: String): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit!!
    }
}