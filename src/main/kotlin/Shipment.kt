package org.example

import androidx.compose.material.Text
import kotlinx.coroutines.delay
import java.io.File
import java.util.*

data class Shipment(
    var status: String = "pending",
    var id: String = "pending",
    var notes: String = "",
    var updateHistory: MutableList<shippingUpdate> = mutableListOf<shippingUpdate>(),
    var expectedDeliveryDateTimestamp: Long = 0,
    var currentLocation: String = "pending"
) {
    private var _updateHistory = mutableListOf<shippingUpdate>()

    val shippingUpdates: List<shippingUpdate>
        get() {
            return _updateHistory.toList()
        }
    fun addNote(note: String) {
        notes = notes + "\n\n" + note
    }
    fun addUpdate(update: String) {
        // How does long work here, update
        var newUpdate = shippingUpdate("reference for last update", update, 100)
        updateHistory.add(newUpdate)
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


    suspend fun start(updatelist: List<String>) {
        while(expectedDeliveryDateTimestamp > 0) {
            delay(1000)
            expectedDeliveryDateTimestamp++
            //println(expectedDeliveryDateTimestamp)
            println("Status: ${status}")
            println("ID: ${id}")
            println("Notes: ${notes}")
            //Text(item.updateHistory)
            println("Expected Delivery Time: ${expectedDeliveryDateTimestamp}")
            println("Current Location: ${currentLocation}")
            println("----------")
            //Read in other updates from DB file
            for (update in updatelist) {
                println("Update list is ${updatelist}")
                val update = update.split(",").map { update.trim() }
                println("Update is ${update}")
//                if (update[1].compareTo("${id}") == 0) {
//                    println("Update was for: ${update[1]}")
//                }
            }
        }
    }
}