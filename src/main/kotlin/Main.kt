import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.launch
import androidx.compose.material.TextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.runBlocking
import org.example.ShippingUpdate
import java.util.*

@Composable
@Preview
fun App(trackingSimulator: TrackingSimulator) {
    val trackerViewHelpers = remember { mutableStateListOf<TrackerViewHelper>() }
    var shipmentID by remember { mutableStateOf("")}

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
    ){
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
            Text("Status: ${item.shipmentStatus}")
            Text("ID: ${item.shipmentId}")
            Text("Notes: ")
            for (item: String in item.shipmentNotes) {
                Text(item)
            }
            Text("Updates: ")
            for (item: ShippingUpdate in item.shipmentUpdateHistory) {
                Text("${item.newStatus} on ${Date(item.timestamp.toLong())}")
            }
            Text("Expected Delivery Time: ${Date(item.expectedDeliveryDate)}")
            Text("Current Location: ${item.shipmentLocation}")
            Text("----------")
//            Button(onClick = { item.stopTracking()
//            }) {
//                Text("Remove")
//            }
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