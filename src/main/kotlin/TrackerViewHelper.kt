import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class TrackerViewHelper() {
    var timeRemaining by mutableStateOf(60)
    var timeRemaining2 by mutableStateOf(60L)
    var textFieldValue by mutableStateOf("60")

    suspend fun startTimer (startTime: Int) {
        timeRemaining = startTime
        val timer = Timer(startTime)
        timer.subscribe {
            timeRemaining = it
        }
        timer.start()
    }
    suspend fun startTimer2 (startTime2: Long) {
        timeRemaining2 = startTime2
        val timer2 = Timer(startTime2.toInt())
        timer2.subscribe {
            timeRemaining2 = it.toLong()
        }
        timer2.start()
    }
}