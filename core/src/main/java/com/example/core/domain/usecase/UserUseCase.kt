package com.example.core.domain.usecase

import com.example.core.domain.model.UserDataModel
import io.reactivex.Flowable

interface UserUseCase {
    fun getData(): Flowable<List<UserDataModel>>
}