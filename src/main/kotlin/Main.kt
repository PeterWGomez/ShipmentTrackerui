import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
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


    // make viewhelper to update ui
    val viewHelper = remember {TrackerViewHelper()}

//    var updateHistory by remember { mutableStateOf(mutableListOf<String>()) }
//    updateHistory.add("First")
//    updateHistory.add("Second")
    Column {
        Row {
            //SearchBarHolder("You don't see this")
            TrackingInput()
        }
//        ResultHolder(shipments)
        for (item: Shipment in shipments){
            Text("Status: ${item.status}")
            Text("ID: ${item.id}")
            Text("Notes: ${item.notes}")
            //Text(item.updateHistory)
            Text("Expected Delivery Time: ${Date(item.expectedDeliveryDateTimestamp)}")
            Text("Current Location: ${item.currentLocation}")
            Text("----------")
            coroutineScope.launch {
                item.start()
            }
        }
    }
}

@Composable
fun SearchBarHolder(name: String) {
    Text("Bar goes here")
}
@Composable
fun ResultHolder(listofshipments: MutableList<Shipment>) {
    Text("test")

    for (item: Shipment in listofshipments){
        Text("Status: ${item.status}")
        Text("ID: ${item.id}")
        Text("Notes: ${item.notes}")
        //Text(item.updateHistory)
        Text("Expected Delivery Time: ${Date(item.expectedDeliveryDateTimestamp)}")
        Text("Current Location: ${item.currentLocation}")
        Text("----------")
//        coroutineScope.launch {
//            item.start()
//        }
    }
}
@Composable
fun TrackingInput() {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Tracking #") }
    )
    Button(onClick = {
    }) {
        Text("Track")
    }

}

fun readFile(fileName: String)
        = File(fileName).forEachLine { println(it) }

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
