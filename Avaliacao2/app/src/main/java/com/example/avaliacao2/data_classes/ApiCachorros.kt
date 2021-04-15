package com.example.avaliacao2.data_classes

import retrofit2.Call
import retrofit2.http.*
interface ApiCachorros {

    @GET("{id}")
    fun get(@Path("id")id:Int):Call<Cachorros>
}