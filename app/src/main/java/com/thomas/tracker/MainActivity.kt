package com.thomas.tracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.*
import com.google.gson.internal.GsonBuildConfig
import com.thomas.tracker.GeocodingService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val LOCATION_PERMISSION_REQUEST_CODE  = 100
    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        checkLocationPermission()
        setContentView(R.layout.activity_main)
    }

    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            requestLastLocation()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestLastLocation()
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show()
                // Handle permission denial (e.g., disable location-dependent features)
            }
        }
    }

    private fun requestLastLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    val latitude = it.latitude
                    val longitude = it.longitude
                    val apiKey = BuildConfig.apiKey
                    // Do something with latitude and longitude
                    Toast.makeText(this, "Latitude: $latitude, Longitude: $longitude", Toast.LENGTH_SHORT).show()
                    val retrofit = Retrofit.Builder()
                        .baseUrl("https://maps.googleapis.com/maps/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                    val geocodingService = retrofit.create(GeocodingService::class.java)
                    val geocodingRepository = GeocodingRepository(geocodingService)
                    lifecycleScope.launch {
                        val address = geocodingRepository.reverseGeocode(latitude, longitude, apiKey!!)
                        Toast.makeText(this@MainActivity, "Address: $address", Toast.LENGTH_SHORT).show()
                    }
                } ?: run {
                    Toast.makeText(this, "Location not found", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error getting location: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }
}