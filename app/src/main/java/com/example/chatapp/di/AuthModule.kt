package com.example.chatapp.di

import com.example.chatapp.data.repository.AuthRepositoryImpl
import com.example.chatapp.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AuthModule {

    @Binds
    @ViewModelScoped
    abstract fun provideAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository
}

//@Module
//@InstallIn(ViewModelComponent::class)
//class AuthModule {
//    @Provides
//    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl()
//}



