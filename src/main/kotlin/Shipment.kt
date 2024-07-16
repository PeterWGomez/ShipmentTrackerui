package org.example

import kotlinx.coroutines.delay

data class Shipment(
    var status: String = "pending",
    var id: String = "pending",
    var notes: MutableList<String> = mutableListOf<String>(),
    var updateHistory: MutableList<ShippingUpdate> = mutableListOf<ShippingUpdate>(),
    var expectedDeliveryDateTimestamp: Long = 0,
    var currentLocation: String = "pending"
) {
    private val subscribers = mutableListOf<(Shipment) -> Unit>()

    fun subscribe(observer: (Shipment) -> Unit) {
        subscribers.add(observer)
    }

    fun unsubscribe(observer: (Shipment) -> Unit) {
        subscribers.remove(observer)
    }

    fun notifyObservers() {
        subscribers.forEach {
            it(this)
        }
    }

    private var _updateHistory = mutableListOf<ShippingUpdate>()

    val ShippingUpdates: List<ShippingUpdate>
        get() {
            return _updateHistory.toList()
        }
    fun addNote(note: String) {
        notes.add(note)
        notifyObservers()
    }
    fun addUpdate(update: ShippingUpdate) {
        updateHistory.add(update)
        if (update.newStatus == "created" || update.newStatus == "shipped" || update.newStatus == "delayed" || update.newStatus == "lost" || update.newStatus == "canceled" || update.newStatus == "delivered") {
            status = update.newStatus
        }
        notifyObservers()
    }
}