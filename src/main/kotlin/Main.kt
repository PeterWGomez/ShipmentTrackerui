import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.Shipment
import org.example.shippingUpdate

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Track") }
    var updateHistory by remember { mutableStateOf(mutableListOf<String>()) }
    var shipments by remember { mutableStateOf(mutableListOf<Shipment>()) }
    updateHistory.add("First")
    updateHistory.add("Second")
    Column {
        Row {
            SearchBarHolder("You don't see this")
            Button(onClick = {
                updateHistory.add("New")
            }) {
                Text("Track")
            }
        }
        ResultHolder(updateHistory)
    }
}

@Composable
fun SearchBarHolder(name: String) {
    Text("Bar goes here")
}
@Composable
fun ResultHolder(listofshipments: MutableList<String>) {
    Text("test")

    for (item: String in listofshipments){
        Text(item)
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
