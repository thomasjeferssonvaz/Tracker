package com.thomas.tracker

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingService {
    @GET("geocode/json")
    suspend fun reverseGeocode(
        @Query("latlng") latlng: String,
        @Query("key") apiKey: String
    ): Response<GeocodingResponse>
}