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
typealias EventResponse = List<EventValue>
data class EventValue(
        @SerializedName("created")
        val created: Long,
        @SerializedName("duration")
        val duration: Long,
        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("rsvp_limit")
        val rsvpLimit: Long,
        @SerializedName("date_in_series_pattern")
        val dateInSeriesPattern: Boolean,
        @SerializedName("status")
        val status: String,
        @SerializedName("time")
        val time: Long,
        @SerializedName("local_date")
        val localDate: String,
        @SerializedName("local_time")
        val localTime: String,
        @SerializedName("updated")
        val updated: Long,
        @SerializedName("utcOffset")
        val utcOffset: Long,
        @SerializedName("waitlist_count")
        val waitlistCount: Long,
        @SerializedName("yes_rsvp_count")
        val yesRsvpCount: Long,
        @SerializedName("venue")
        val venue: Venue? = null,
        @SerializedName("group")
        val group: Group,
        @SerializedName("link")
        val link: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("how_to_find_us")
        val howToFindUs: String? = null,
        @SerializedName("visibility")
        val visibility: String,
        @SerializedName("rsvp_open_offset")
        val rsvpOpenOffset: String? = null,
        @SerializedName("rsvp_close_offset")
        val rsvpCloseOffset: String? = null
)

data class Venue(
        @SerializedName("id")
        val id: Long,
        @SerializedName("name")
        val name: String,
        @SerializedName("lat")
        val lat: Double,
        @SerializedName("lon")
        val lon: Double,
        @SerializedName("repinned")
        val repinned: Boolean,
        @SerializedName("address_1")
        val address1: String,
        @SerializedName("city")
        val city: String,
        @SerializedName("country")
        val country: String,
        @SerializedName("localized_country_name")
        val localizedCountryName: String
)

data class Group(
        @SerializedName("created")
        val created: Long,
        @SerializedName("name")
        val name: String,
        @SerializedName("id")
        val id: Long,
        @SerializedName("join_mode")
        val joinMode: String,
        @SerializedName("lat")
        val lat: Double,
        @SerializedName("lon")
        val lon: Double,
        @SerializedName("urlname")
        val urlname: String,
        @SerializedName("who")
        val who: String,
        @SerializedName("localized_location")
        val localizedLocation: String,
        @SerializedName("state")
        val state: String,
        @SerializedName("country")
        val country: String,
        @SerializedName("region")
        val region: String,
        @SerializedName("timezone")
        val timezone: String
)