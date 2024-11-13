package com.dicoding.eventdicoding.data.retrofit

import com.dicoding.eventdicoding.data.response.DetailResponse
import com.dicoding.eventdicoding.data.response.ResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("events")
   suspend fun getEvents(
       @Query("active") active: Int
   ): Response<ResponseModel>

   @GET("events/{id}")
   suspend fun getDetailEvent(@Path("id") id: Int): Response<DetailResponse>
}