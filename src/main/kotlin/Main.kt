import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.Shipment
import java.io.File

@Composable
@Preview
fun App() {
    // make shipments object list
    var shipments by remember { mutableStateOf(mutableListOf<Shipment>()) }

    // read in the file and fill the shipments
    //readFile("testcreate.txt")
    File("testcreate.txt").forEachLine {
        //println(it)
        val parts = it.split(",").map { it.trim() }
        var shipment = Shipment(parts[1])
        shipments.add(shipment)
    }


    // make viewhelper to update ui
    val viewHelper = remember {TrackerViewHelper(shipments)}

//    var updateHistory by remember { mutableStateOf(mutableListOf<String>()) }
//    updateHistory.add("First")
//    updateHistory.add("Second")
    Column {
        Row {
            //SearchBarHolder("You don't see this")
            TrackingInput()
        }
        ResultHolder(shipments)
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
        Text(item.status)
        Text(item.id)
        Text(item.notes)
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
