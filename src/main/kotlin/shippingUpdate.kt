package org.example

data class shippingUpdate(
    var previousStatus: String,
    var newStatus: String,
    var timestamp: Long
) {
    fun getPoint(){
        println("hi")
    }
}