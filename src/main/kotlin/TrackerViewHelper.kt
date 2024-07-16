import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.example.Shipment

class TrackerViewHelper() {
    var shipmentId: String by mutableStateOf("")
        private set
    var shipmentTotes: MutableList<String> = mutableListOf()
        private set
    var shipmentUpdateHistory: MutableList<String> = mutableListOf()
        private set
    var expectedDeliveryDate: MutableList<String> = mutableListOf()
        private set
    var shipmentStatus: String by mutableStateOf("")
        private set

    var shipments: MutableList<Shipment> = mutableListOf()
        private set

    fun trackShipment (shipment: Shipment) {

        shipment.subscribe {
            shipmentStatus = it.status
        }
    }

    fun stopTracking() {
        println("stopTracking was called")
        //tracksim2.unsubscribe
    }
}