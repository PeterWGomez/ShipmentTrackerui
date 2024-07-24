package org.example

import BulkShipment
import ExpressShipment
import OverShipment

data class Shipment (
    var status: String = "pending",
    var id: String = "pending",
    var notes: MutableList<String> = mutableListOf<String>(),
    var updateHistory: MutableList<ShippingUpdate> = mutableListOf<ShippingUpdate>(),
    var createdTimestamp: Long = 0,
    var currentLocation: String = "pending",
    var shipmentType: String = "pending"
) : BulkShipment, ExpressShipment, OverShipment {
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

    fun addNote(note: String) {
        notes.add(note)
        notifyObservers()
    }
    fun addUpdate(update: ShippingUpdate) {
        updateHistory.add(update)
        if (update.newStatus == "created" || update.newStatus == "shipped" || update.newStatus == "delayed" || update.newStatus == "lost" || update.newStatus == "canceled" || update.newStatus == "delivered") {
            status = update.newStatus
        }
        if (update.newStatus == "created") {
            shipmentType = update.shipmentType
            createdTimestamp = update.timestamp
        }
        if (update.newStatus == "shipped") {
            if (!checkBulk(update.timestamp, createdTimestamp) && id == "s10000") {
                addNote("WARNING: Bulk shipment arrived early. Sending package agents to retrieve the package")
            }
            if (!checkExpress(update.timestamp, createdTimestamp) && id == "s10002") {
                addNote("WARNING: Express shipment arrived late. Sending package agents to retrieve the package")
            }
            if (!checkOvernight(update.timestamp, createdTimestamp) && id == "s10003") {
                addNote("WARNING: Overnight shipment arrived late. Sending package agents to retrieve the package")
            }
        }
        notifyObservers()
    }
}