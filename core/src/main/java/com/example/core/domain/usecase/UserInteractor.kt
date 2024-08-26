package com.example.core.domain.usecase

import com.example.core.domain.model.UserDataModel
import com.example.core.domain.repository.IUserRepository
import io.reactivex.Flowable
import javax.inject.Inject

class UserInteractor @Inject constructor(
    private val userRepository: IUserRepository
) : UserUseCase {

    override fun getData(): Flowable<List<UserDataModel>> {
        return userRepository.getData()
    }

}