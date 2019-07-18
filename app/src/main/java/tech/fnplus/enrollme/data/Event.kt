package tech.fnplus.enrollme.data

import com.google.gson.annotations.SerializedName

data class Event(
        val id: String,
        val name: String,
        val link: String,
        val city: String,
        val description: String,
        val members: String
)

data class EventValue(
        @SerializedName("created")
        val created: Long,
        @SerializedName("duration")
        val duration: Long,
        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("rsvpLimit")
        val rsvpLimit: Long,
        @SerializedName("dateInSeriesPattern")
        val dateInSeriesPattern: Boolean,
        @SerializedName("status")
        val status: String,
        @SerializedName("time")
        val time: Long,
        @SerializedName("localDate")
        val localDate: String,
        @SerializedName("localTime")
        val localTime: String,
        @SerializedName("updated")
        val updated: Long,
        @SerializedName("utcOffset")
        val utcOffset: Long,
        @SerializedName("waitlistCount")
        val waitlistCount: Long,
        @SerializedName("yesRsvpCount")
        val yesRsvpCount: Long,
        @SerializedName("venue")
        val venue: Venue? = null,
        @SerializedName("group")
        val group: Group,
        @SerializedName("link")
        val link: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("howToFindUs")
        val howToFindUs: String? = null,
        @SerializedName("visibility")
        val visibility: String,
        @SerializedName("rsvpOpenOffset")
        val rsvpOpenOffset: String? = null,
        @SerializedName("rsvpCloseOffset")
        val rsvpCloseOffset: String? = null
)

data class Venue(
        val id: Long,
        val name: String,
        val lat: Double,
        val lon: Double,
        val repinned: Boolean,
        val address1: String,
        val city: String,
        val country: String,
        val localizedCountryName: String
)

data class Group(
        val created: Long,
        val name: String,
        val id: Long,
        val joinMode: String,
        val lat: Double,
        val lon: Double,
        val urlname: String,
        val who: String,
        val localizedLocation: String,
        val state: String,
        val country: String,
        val region: String,
        val timezone: String
)