package com.atvantiq.parqngomall.data.repository.auth

import com.atvantiq.parqngomall.data.model.Posts


interface IAuthRepo {
    //suspend fun loginRequest(): Flow<Posts>
    suspend fun loginRequest(): Posts

}