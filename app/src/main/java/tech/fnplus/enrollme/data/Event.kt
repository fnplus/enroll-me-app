package tech.fnplus.enrollme.data

data class Event(val id: Int,
                 val name: String,
                 val link: String,
                 val city: String,
                 val description: String,
                 val members: String
)

data class EventNew(val id: Int,
                 val name: String,
                 val link: String,
                 val city: String,
                 val description: String,
                 val members: String,
                 val time: Long,
                 val utc_offset: Int,
                 val duration: Long?,
                 val updated: Long,
                 val venue: EventVenue?
)

data class AccessResponse(val access_token: String,
                          val token_type: String,
                          val expires_in: Long,
                          val refresh_token: String
)

data class EventVenue(val id: String,
                      val name: String,
                      val address_1: String?,
                      val address_2: String?,
                      val address_3: String?,
                      val city: String,
                      val localized_country_name: String
)

