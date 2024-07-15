import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.Shipment
import org.example.shippingUpdate
import java.io.File
import java.util.*

@Composable
@Preview
fun App() {
    val trackerViewHelper = remember { TrackerViewHelper() }
    val coroutineScope = rememberCoroutineScope()

    // make shipments object list
    var shipments by remember { mutableStateOf(mutableListOf<Shipment>()) }

    // read in the file and fill the shipments
    File("testcreate.txt").forEachLine {
        //println(it)
        val parts = it.split(",").map { it.trim() }
        var updateHistory = mutableListOf<shippingUpdate>()
        var shipment = Shipment(parts[0], parts[1],"",updateHistory, parts[2].toLong())
        shipments.add(shipment)
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
        for (item: Shipment in shipments){
            coroutineScope.launch {
                item.start()
                //trackerViewHelper.startTimer2(item.expectedDeliveryDateTimestamp)
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
    // The UI has failed, defaulting to console for now to make sure the program actually works
    //Read in other updates to DB file
    File("testupdates.txt").forEachLine {
        //println(it)
        val databasefile = it.split(",").map { it.trim() }

    }
}


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
