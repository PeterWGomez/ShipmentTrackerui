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
import org.example.ShippingUpdate
import java.io.File

@Composable
@Preview
fun App() {
    val trackerViewHelper = remember { TrackerViewHelper() }
    val coroutineScope = rememberCoroutineScope()

    // initialize simulator, coroutine launch needed for suspend functions to work
    var trackingSimulator = remember {TrackingSimulator()}
    coroutineScope.launch {
        trackingSimulator.runSimulation()
    }

    //---------------------------------

    // make shipments object list -- to be deleted
    var shipments by remember { mutableStateOf(mutableListOf<Shipment>()) }

    // read file into a list
    val dbfile: List<String> = File("testupdates.txt").readLines()


    // read in the file and fill the shipments
    File("testcreate.txt").forEachLine {
        //println(it)
        val parts = it.split(",").map { it.trim() }
        var updateHistory = mutableListOf<ShippingUpdate>()
//        var shipment = Shipment(parts[0], parts[1],"",updateHistory, parts[2].toLong())
//        shipments.add(shipment)
    }

    Column {
        Button(onClick = {
            coroutineScope.launch {
                trackerViewHelper.startTimer(trackerViewHelper.textFieldValue.toInt())
            }
        }) {
            Text("Track")
        }
        Text("${trackerViewHelper.timeRemaining}")
        Row {

        }
        for (item: Shipment in trackingSimulator.shipments){
            coroutineScope.launch {
            }
            Text("Status: ${item.status}")
            Text("ID: ${item.id}")
            Text("Notes: ${item.notes}")
            //Text(item.updateHistory)
            Text("Expected Delivery Time: ${trackerViewHelper.timeRemaining2}")
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
