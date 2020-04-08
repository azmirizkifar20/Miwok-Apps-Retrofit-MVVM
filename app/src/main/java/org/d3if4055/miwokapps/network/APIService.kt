@file:Suppress("SpellCheckingInspection")

package org.d3if4055.miwokapps.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if4055.miwokapps.model.BASE_URL
import org.d3if4055.miwokapps.model.GET_MIWOK
import org.d3if4055.miwokapps.model.Miwok
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

//API
interface MiwokService {
    @GET(GET_MIWOK)
    fun showList(): Call<List<Miwok>>
}

object MiwokApi {
    val retrofitService: MiwokService = retrofit.create(MiwokService::class.java)
}