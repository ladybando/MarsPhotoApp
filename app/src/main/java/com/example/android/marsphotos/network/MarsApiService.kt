package com.example.android.marsphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


class MarsApiService {
    /**
     * A public interface that exposes the [getPhotos] method
     */
    interface MarsApiService {
        /**
         *
         * The @GET annotation indicates that the "photos" endpoint will be requested with the GET
         * HTTP method
         */
        @GET("photos")
        suspend fun getPhotos(): List<MarsPhoto>
    }

    companion object {


        private const val BASE_URL =
            "https://android-kotlin-fun-mars-server.appspot.com"

        /*
  * Retrofit takes base URI for web services
  * and converter factory to build webs services api.
  * Retrofit  fetches JSON response and MoshiConverter
  * converts response to string*/

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        private val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()

        /**
         * A public Api object that exposes the lazy-initialized Retrofit service
         */
        val retrofitService: MarsApiService by lazy {
            retrofit.create(MarsApiService::class.java)
        }

    }
}
