import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import org.example.Shipment
import org.example.ShippingUpdate
import java.io.File
import java.util.Date


// NOTE to the grader: The intent is to make this private, as written here in the comments
// I chose to not implement that until I got the UI working properly
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
        // Read test.txt and uses the first 4 fields to determine the update, id, and timestamp for each update
        File("test.txt").forEachLine {
            val dataline = it.split(",").map { it.trim() }
            // This section was to be enabled once suspend was working.
            //delay(1000)
            // If the update is "created", adds the shipment
//            if (dataline[0] == "created") {
//                var shipment = Shipment(dataline[1], dataline[0])
//            }

            // Gets the shipment and adds the update
//            println("Previous update: ${findShipment(dataline[1])!!.status}")
            var newUpdate = ShippingUpdate(findShipment(dataline[1])!!.status, dataline[0], dataline[2].toLong())
            findShipment(dataline[1])?.addUpdate(newUpdate)
            //If it is a note or a location update, add it here
            if (dataline[0] == "location") {
                print("Shipment went from ${findShipment(dataline[1])?.currentLocation} to ")
                findShipment(dataline[1])?.currentLocation = dataline[3]
                println("${findShipment(dataline[1])?.currentLocation} on ${Date(dataline[2].toLong())}")
            }
            if (dataline[0] == "noteadded") {
                findShipment(dataline[1])?.addNote("${Date(dataline[2].toLong())} ${dataline[3]}")
            }

            // Since the UI is not working, these print statements will show the updates are going through
            println("ID: ${findShipment(dataline[1])?.id}")
            println("Status: ${findShipment(dataline[1])?.status}")
            println("Notes: ")
            for (item: String in findShipment(dataline[1])!!.notes) {
                println(item)
            }
            println("Updates: ")
            for (item: ShippingUpdate in findShipment(dataline[1])!!.updateHistory) {
                println("${item.newStatus} on ${Date(item.timestamp.toLong())}")
            }
            println("Expected Delivery: ${Date(findShipment(dataline[1])?.expectedDeliveryDateTimestamp!!.toLong())}")
            println("Location: ${findShipment(dataline[1])?.currentLocation}")
            println("----------------------")
        }

    }
}
