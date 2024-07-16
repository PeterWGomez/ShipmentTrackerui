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

    suspend fun trackShipment (tracksim: TrackingSimulator) {
        shipments = tracksim.shipments
        var tracksim2 = TrackingSimulator()
        tracksim2.shipments = shipments
        tracksim2.subscribe {
            tracksim2.shipments = it
        }
        tracksim2.runSimulation()
    }

    fun stopTracking() {
        println("stopTracking was called")
        //tracksim2.unsubscribe
    }
}