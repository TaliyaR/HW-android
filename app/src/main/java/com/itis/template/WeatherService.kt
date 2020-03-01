package com.itis.template

import com.itis.template.response.WeatherList
import com.itis.template.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    suspend fun weatherByName(@Query("q") name: String?): WeatherResponse

    @GET("weather")
    suspend fun weatherByID(@Query("id") id: Int): WeatherResponse

    @GET("find")
    suspend fun citiesInCycle(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("cnt") cnt: Int
    ): WeatherList
}
