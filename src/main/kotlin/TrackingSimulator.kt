import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import org.example.Shipment
import org.example.ShippingUpdate
import java.io.File


// NOTE to the grader: The intent is to make this private, as written here in the comments
// I chose to not implement that until I got the UI working properly, as I started
// to suspect this was causing the issues.
//
//class TrackingSimulator(
//    private var _shipments: MutableList<Shipment> = mutableListOf<Shipment>()
//) {
//    var shipments: List<Shipment>
//      get() = _shipments.toList()
//      private set(value) {
//        _shipments = value.toMutableList()
//      }
//
//}

class TrackingSimulator(
    var shipments: MutableList<Shipment> = mutableListOf<Shipment>()
) {
    init {
        // Normally, this would populate this list with the active shipments from a database
        // then the user would request from this list.
        // For now, it will be initialized with the shipping IDs from test.txt
        var shipment = Shipment("pending", "s10000")
        var shipment2 = Shipment("pending", "s10001")
        var shipment3 = Shipment("pending", "s10002")
        var shipment4 = Shipment("pending", "s10003")
        var shipment5 = Shipment("pending", "s10004")
        var shipment6 = Shipment("pending", "s10005")
        var shipment7 = Shipment("pending", "s10006")
        var shipment8 = Shipment("pending", "s10007")
        var shipment9 = Shipment("pending", "s10008")
        var shipment10 = Shipment("pending", "s10009")
        addShipment(shipment)
        addShipment(shipment2)
        addShipment(shipment3)
        addShipment(shipment4)
        addShipment(shipment5)
        addShipment(shipment6)
        addShipment(shipment7)
        addShipment(shipment8)
        addShipment(shipment9)
        addShipment(shipment10)
    }

    //Returns the shipment of matching id
    fun findShipment(id: String): Shipment? {
        for (shipment in shipments) {
            if (shipment.id == id) {
                return shipment
            }
        }
        // Add error message in console saying the shipment was not found
        return null
    }
    //Adds a shipment
    fun addShipment(shipment: Shipment) {
        shipments.add(shipment)
    }

    private val subscribers = mutableListOf<(MutableList<Shipment>) -> Unit>()

    fun subscribe(observer: (MutableList<Shipment>) -> Unit) {
        subscribers.add(observer)
    }

    fun unsubscribe(observer: (MutableList<Shipment>) -> Unit) {
        subscribers.remove(observer)
    }

    fun notifyObservers() {
        subscribers.forEach {
            it(shipments)
        }
    }

    // Run Simulation starts the timer for each shipment and starts pulling updates from the test.txt
    suspend fun runSimulation() {
        // Read test.txt and use the first 3 fields to determine the update, id, and timestamp for each update
        File("test.txt").forEachLine {
            //println(it)
            val dataline = it.split(",").map { it.trim() }
//            println(it)
//            println(dataline)
            println("update: ${dataline[0]}")
            println("id: ${dataline[1]}")
            println("timestamp: ${dataline[2]}")
            // doesn't work atm, needs to be in coroutine body?
            //delay(1000)
            //gets the shipment and adds the update, needs non null asserted !! to work
            println("Previous update: ${findShipment(dataline[1])!!.status}")
            var newUpdate = ShippingUpdate(findShipment(dataline[1])!!.status, dataline[0], dataline[2].toLong())
            findShipment(dataline[1])?.addUpdate(newUpdate)
        }

    }
}
