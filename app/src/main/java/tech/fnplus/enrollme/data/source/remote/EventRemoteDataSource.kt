package tech.fnplus.enrollme.data.source.remote

import tech.fnplus.enrollme.data.Event
import tech.fnplus.enrollme.data.source.EventDataSource

object EventRemoteDataSource: EventDataSource {
    override fun getEvents(callback: EventDataSource.LoadEventsCallback): List<Event> {

        return listOf()
    }

    override fun saveEvents(events: List<Event>) {

    }
}