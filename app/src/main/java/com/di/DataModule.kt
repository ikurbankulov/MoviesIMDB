package com.di

import com.data.repository.RepositoryImpl
import com.domain.repository.Repository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    fun bindRepository(impl: RepositoryImpl): Repository
}