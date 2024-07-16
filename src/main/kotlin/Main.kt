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

@Composable
@Preview
fun App() {
    val trackerViewHelper = remember { TrackerViewHelper() }
    val coroutineScope = rememberCoroutineScope()

    // initialize simulator, coroutine launch needed for suspend functions to work
    var trackingSimulator = remember {TrackingSimulator()}

    Column {
        Row {
            Button(onClick = {
                coroutineScope.launch {
                    // This updates the ui, but is not delayable
                    trackingSimulator.runSimulation()
                    trackerViewHelper.trackShipment(trackingSimulator)
                }
            }) {
                Text("Track")
            }
            TextField(value = "", onValueChange = {
                null
            })

        }
        for (item: Shipment in trackingSimulator.shipments){
//        for (item: Shipment in trackerViewHelper.shipments){
            Text("Status: ${item.status}")
            Text("ID: ${item.id}")
            Text("Notes: ${item.notes}")
            //Text(item.updateHistory)
            Text("Expected Delivery Time: ${item.expectedDeliveryDateTimestamp}")
            Text("Current Location: ${item.currentLocation}")
            Text("----------")
        }
    }
}


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
