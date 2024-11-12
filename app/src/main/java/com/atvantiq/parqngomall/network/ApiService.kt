package com.atvantiq.parqngomall.network
import com.atvantiq.parqngomall.data.model.Posts
import retrofit2.http.GET

interface ApiService {
	/***
	 * Network calls
	 */
	@GET(NetworkEndPoints.loginRequest)
	suspend fun loginRequest(): Posts
}