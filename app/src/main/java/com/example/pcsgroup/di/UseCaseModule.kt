package com.example.pcsgroup.di

import com.example.core.domain.usecase.UserInteractor
import com.example.core.domain.usecase.UserUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun provideUserUseCase(userInteractor: UserInteractor): UserUseCase

}