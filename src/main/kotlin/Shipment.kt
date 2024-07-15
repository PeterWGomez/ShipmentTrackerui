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
    private var _updateHistory = mutableListOf<ShippingUpdate>()

    val ShippingUpdates: List<ShippingUpdate>
        get() {
            return _updateHistory.toList()
        }
    fun addNote(note: String) {
        notes.add(note)
    }
    // Change to take updates
    fun addUpdate(update: ShippingUpdate) {
        // How does long work here, update
//        var newUpdate = ShippingUpdate("reference for last update", update, 100)
        updateHistory.add(update)
        if (update.newStatus == "created" || update.newStatus == "shipped" || update.newStatus == "delayed" || update.newStatus == "lost" || update.newStatus == "canceled" || update.newStatus == "delivered") {
            status = update.newStatus
        }
    }

    private val subscribers = mutableListOf<(Long) -> Unit>()

    fun subscribe(observer: (Long) -> Unit) {
        subscribers.add(observer)
    }

    fun unsubscribe(observer: (Long) -> Unit) {
        subscribers.remove(observer)
    }

    fun notifyObservers() {
        subscribers.forEach {
            it(expectedDeliveryDateTimestamp)
        }
    }
}