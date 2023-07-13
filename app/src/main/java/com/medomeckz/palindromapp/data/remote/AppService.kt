package com.medomeckz.palindromapp.data.remote

import com.medomeckz.palindromapp.data.model.ResponseUser
import retrofit2.http.GET
import retrofit2.http.Query

interface AppService {
    @GET("users")
    suspend fun getUser(
       @Query("page") page: Int,
       @Query("per_page") per_page: Int
    ): ResponseUser
}