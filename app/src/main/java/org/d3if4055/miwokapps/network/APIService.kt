package org.d3if4055.miwokapps.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if4055.miwokapps.data.Miwok
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

//API
interface MiwokService {
    @GET("miwok-v2.php")
    fun showList(): Call<List<Miwok>>
}

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl("http://dif.indraazimi.com/")
    .build()

object MiwokApi {
    val retrofitService: MiwokService by lazy {
        retrofit.create(MiwokService::class.java)
    }
}