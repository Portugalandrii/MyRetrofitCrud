package com.portugal.andrii.myretrofitcrud

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET



interface CrudInterface {
    @GET("api/books/")  // запрос методом GET

    fun getCrud() : Call<List<Crud>>   //функция выводит объект класса Call,
                                       // который содержит результат работы сервера

    companion object {   // сингитон - статический объект одиночка, для обращения к которому не требуется создание класса

        var BASE_URL = "https://spring-boot-mysql-server-part0.herokuapp.com/"

        fun create() : CrudInterface {    //эта функция реализует интерфейс BankService с помощью класса Retrofit

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(CrudInterface::class.java)

        }
    }
}