package org.example

data class ShippingUpdate(
    var previousStatus: String,
    var newStatus: String,
    var timestamp: Long,
    var shipmentType: String = ""
) {
    
}