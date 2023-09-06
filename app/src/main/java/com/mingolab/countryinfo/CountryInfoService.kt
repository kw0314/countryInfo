package com.mingolab.countryinfo


import retrofit2.Response
import retrofit2.http.GET

interface CountryInfoService {

    // for coroutine
    @GET("/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/")
    suspend fun getCountryDataCoroutine(
//        @Query("KEY") KEY : String,
//        @Query("TYPE") Type : String
    ): Response<List<Country>>
}