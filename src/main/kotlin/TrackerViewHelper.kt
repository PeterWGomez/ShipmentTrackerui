import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.example.Shipment
import javax.sound.midi.Track

class TrackerViewHelper() {
    var timeRemaining by mutableStateOf(60)
    var shipments: MutableList<Shipment> = mutableListOf<Shipment>()
    var textFieldValue by mutableStateOf("60")

    suspend fun startTimer (startTime: Int) {
        timeRemaining = startTime
        val timer = Timer(startTime)
        timer.subscribe {
            timeRemaining = it
        }
        timer.start()
    }
    suspend fun startTimer2 () {
        val timer2 = TrackingSimulator()
        timer2.subscribe {
            shipments = it
        }
        timer2.runSimulation()
    }
}