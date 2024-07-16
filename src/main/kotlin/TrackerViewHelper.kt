import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.example.Shipment

class TrackerViewHelper() {
    var timeRemaining by mutableStateOf(60)
    var shipments: MutableList<Shipment> = mutableListOf<Shipment>()

    suspend fun startTimer (tracksim: TrackingSimulator) {
        shipments = tracksim.shipments
        var tracksim2 = TrackingSimulator()
        tracksim2.shipments = shipments
        tracksim2.subscribe {
            tracksim2.shipments = it
        }
        tracksim2.runSimulation()
    }
}