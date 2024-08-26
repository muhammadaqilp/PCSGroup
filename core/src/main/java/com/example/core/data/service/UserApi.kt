package com.example.core.data.service

import com.example.core.data.response.UserDataResponse
import io.reactivex.Flowable
import retrofit2.http.GET

interface UserApi {

    @GET("getData/test")
    fun getData(): Flowable<List<UserDataResponse>>

}