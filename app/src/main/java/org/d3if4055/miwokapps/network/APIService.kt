package org.d3if4055.miwokapps.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if4055.miwokapps.data.Miwok
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl("http://dif.indraazimi.com/")
    .build()

//API
interface MiwokService {
    @GET("miwok-v2.php")
    suspend fun showList(): List<Miwok>
}

object MiwokApi {
    val retrofitService = retrofit.create(MiwokService::class.java)
}