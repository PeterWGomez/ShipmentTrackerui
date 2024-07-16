import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.launch
import org.example.Shipment
import androidx.compose.material.TextField
import kotlinx.coroutines.runBlocking

@Composable
@Preview
fun App(trackingSimulator: TrackingSimulator) {
    val trackerViewHelpers = remember { mutableStateListOf<TrackerViewHelper>() }
    var shipmentID by remember { mutableStateOf("")}

    Column {
        Row {
            Button(onClick = {
                val shipment = trackingSimulator.findShipment(shipmentID)
                if (shipment != null) {
                    val trackerViewHelper = TrackerViewHelper()
                    trackerViewHelper.trackShipment(shipment)
                    trackerViewHelpers.add(trackerViewHelper)
                }
            }) {
                Text("Track")
            }
            TextField(value = shipmentID, onValueChange = {
                shipmentID = it
            })

        }
        for (item in trackerViewHelpers){
//        for (item: Shipment in trackerViewHelper.shipments){
            Text("Status: ${item.shipmentStatus}")
//            Text("ID: ${item.id}")
//            Text("Notes: ${item.notes}")
//            //Text(item.updateHistory)
//            Text("Expected Delivery Time: ${item.expectedDeliveryDateTimestamp}")
//            Text("Current Location: ${item.currentLocation}")
            Text("----------")
        }
    }
}


fun main() = runBlocking {
    var trackingSimulator = TrackingSimulator()
    launch { trackingSimulator.runSimulation() }
    application {
        Window(onCloseRequest = ::exitApplication) {
            App(trackingSimulator)
        }
    }
}