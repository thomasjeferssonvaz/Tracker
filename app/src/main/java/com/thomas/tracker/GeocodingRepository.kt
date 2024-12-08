package com.thomas.tracker

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GeocodingRepository(private val geocodingService: GeocodingService) {  // Dependency Injection

    suspend fun reverseGeocode(latitude: Double, longitude: Double, apiKey: String): String? = withContext(Dispatchers.IO) {
        try {
            val response = geocodingService.reverseGeocode("${latitude},${longitude}", apiKey)
            if (response.isSuccessful) {
                val geocodingResponse = response.body()
                geocodingResponse?.results?.firstOrNull()?.formattedAddress
            } else {
                println("Error: ${response.code()} - ${response.errorBody()?.string()}")
                null
            }
        } catch (e: Exception) {
            println("Exception: ${e.message}")
            null
        }
    }
}