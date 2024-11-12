package com.atvantiq.parqngomall.data.repository.auth

import com.atvantiq.parqngomall.data.model.Posts
import com.atvantiq.parqngomall.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthRepo @Inject constructor(private val apiService: ApiService) : IAuthRepo {

    override suspend fun loginRequest():Posts = apiService.loginRequest()

    /* override suspend fun loginRequest(): Flow<Posts> {
         return flow {
             var response  = apiService.loginRequest()
             emit(response)
         }.flowOn(Dispatchers.IO)
     }*/

}