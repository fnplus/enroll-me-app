package tech.fnplus.enrollme.data.source

import tech.fnplus.enrollme.data.Event

interface EventDataSource {

    interface LoadEventsCallback {

        fun onEventsLoaded(events: List<Event>)

        fun onDataNotAvailable(error: String)
    }

    fun getEvents(callback: LoadEventsCallback) : List<Event>

    fun saveEvents(events: List<Event>)
}