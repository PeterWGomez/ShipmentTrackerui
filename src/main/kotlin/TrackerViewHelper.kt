import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.example.Shipment
import org.example.ShippingUpdate

class TrackerViewHelper() {
    var shipmentId: String by mutableStateOf("")
        private set
    var shipmentNotes: MutableList<String> = mutableListOf()
        private set
    var shipmentUpdateHistory: MutableList<ShippingUpdate> = mutableListOf()
        private set
    var expectedDeliveryDate: Long by mutableStateOf(0L)
        private set
    var shipmentStatus: String by mutableStateOf("")
        private set
    var shipmentLocation: String by mutableStateOf("")
        private set

    fun trackShipment (shipment: Shipment) {

        shipment.subscribe {
            shipmentStatus = it.status
            shipmentId = it.id
            shipmentNotes = it.notes
            shipmentUpdateHistory = it.updateHistory
            expectedDeliveryDate = it.expectedDeliveryDateTimestamp
            shipmentLocation = it.currentLocation
        }
    }

    fun stopTracking() {
        println("stopTracking was called")
//        shipmentHolder.id = idfinish
//        .unsubscribe{}
    }
}