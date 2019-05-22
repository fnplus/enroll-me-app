package tech.fnplus.enrollme.data.source

import tech.fnplus.enrollme.data.Event

class EventRepository(val eventRemoteDataSource: EventDataSource): EventDataSource {
    override fun getEvents(callback: EventDataSource.LoadEventsCallback): List<Event> {

        return listOf()
    }

    override fun saveEvents(events: List<Event>) {

    }
}