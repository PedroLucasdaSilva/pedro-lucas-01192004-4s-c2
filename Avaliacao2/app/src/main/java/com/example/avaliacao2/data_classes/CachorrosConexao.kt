package com.example.avaliacao2.data_classes

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CachorrosConexao {

    fun criar(): ApiCachorros{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://5f861cfdc8a16a0016e6aacd.mockapi.io/bandtec-api/cachorros/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val api = retrofit.create(ApiCachorros::class.java)


        return api
    }
}