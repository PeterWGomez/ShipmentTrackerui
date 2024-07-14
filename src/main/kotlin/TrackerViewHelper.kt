import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.example.Shipment

class TrackerViewHelper(private var shipments: String) {
    var updateHistory by remember { mutableStateOf(mutableListOf<String>()) }
}