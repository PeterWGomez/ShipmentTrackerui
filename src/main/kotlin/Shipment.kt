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
            var delayflag = false
            if (!checkBulk(update.timestamp, createdTimestamp) && shipmentType == "BulkShipment") {
                for (paststatus in updateHistory) {
                    if (paststatus.newStatus == "delayed") {
                        delayflag = true
                    }
                }
                if (!delayflag) {
                    addNote("WARNING: Bulk shipment will arrive early.")
                }
            }
            if (!checkExpress(update.timestamp, createdTimestamp) && shipmentType == "ExpressShipment") {
                for (paststatus in updateHistory) {
                    if (paststatus.newStatus == "delayed") {
                        delayflag = true
                    }
                }
                if (!delayflag) {
                    addNote("WARNING: Express shipment will arrive late.")
                }
            }
            if (!checkOvernight(update.timestamp, createdTimestamp) && shipmentType == "OvernightShipment") {
                for (paststatus in updateHistory) {
                    if (paststatus.newStatus == "delayed") {
                        delayflag = true
                    }
                }
                if (!delayflag) {
                    addNote("WARNING: Overnight shipment will arrive late.")
                }
            }
        }
        notifyObservers()
    }
}