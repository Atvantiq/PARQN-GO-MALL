package com.atvantiq.parqngomall.di.modules

import com.atvantiq.parqngomall.data.repository.auth.AuthRepo
import com.atvantiq.parqngomall.data.repository.auth.IAuthRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepoModule() {

	@Provides
	@Singleton
	fun provideIAuthRepo(authRepo: AuthRepo):IAuthRepo = authRepo
}
