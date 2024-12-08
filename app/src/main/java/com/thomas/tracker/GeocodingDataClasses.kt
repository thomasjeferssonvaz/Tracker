package com.thomas.tracker

import com.google.gson.annotations.SerializedName

data class GeocodingResponse(
    @SerializedName("results") val results: List<GeocodingResultItem>? = emptyList(),
    @SerializedName("status") val status: String? = null
)

data class GeocodingResultItem(
    @SerializedName("address_components") val addressComponents: List<AddressComponent>? = null,
    @SerializedName("formatted_address") val formattedAddress: String? = null,
    @SerializedName("geometry") val geometry: Geometry? = null,
    @SerializedName("place_id") val placeId: String? = null,
    @SerializedName("types") val types: List<String>? = null
)

data class Geometry(
    @SerializedName("location") val location: Location? = null,
    @SerializedName("location_type") val locationType: String? = null,
    @SerializedName("viewport") val viewport: Viewport? = null
)

data class Location(
    @SerializedName("lat") val latitude: Double? = null,
    @SerializedName("lng") val longitude: Double? = null
)

data class Viewport(
    @SerializedName("northeast") val northeast: Location? = null,
    @SerializedName("southwest") val southwest: Location? = null
)

data class AddressComponent(
    @SerializedName("long_name") val longName: String? = null,
    @SerializedName("short_name") val shortName: String? = null,
    @SerializedName("types") val types: List<String>? = null
)