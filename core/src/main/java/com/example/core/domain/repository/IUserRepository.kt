package com.example.core.domain.repository

import com.example.core.domain.model.UserDataModel
import io.reactivex.Flowable

interface IUserRepository {
    fun getData(): Flowable<List<UserDataModel>>
}