import kotlinx.coroutines.delay
import org.example.Shipment
import org.example.ShippingUpdate
import java.io.File
import java.util.Date

class TrackingSimulator(
    var shipments: MutableList<Shipment> = mutableListOf<Shipment>()
) {
    init {
        for (line in File("test.txt").readLines()) {
            val dataline = line.split(",").map { it.trim() }
            if (dataline[0] == "created") {
                var shipment = Shipment(id = dataline[1])
                shipments.add(shipment)
                var newUpdate = ShippingUpdate(findShipment(dataline[1])!!.status, dataline[0], dataline[2].toLong())
                findShipment(dataline[1])?.addUpdate(newUpdate)
            }
        }
    }

    //Returns the shipment of matching id
    fun findShipment(id: String): Shipment? {
        for (shipment in shipments) {
            if (shipment.id == id) {
                return shipment
            }
        }
        return null
    }
    //Adds a shipment
    fun addShipment(shipment: Shipment) {
        shipments.add(shipment)
    }


    // Run Simulation starts the timer for each shipment and starts pulling updates from the test.txt
    suspend fun runSimulation() {
        // Read test.txt and uses the first 4 fields to determine the update, id, and timestamp for each update
        for (line in File("test.txt").readLines()) {
            val dataline = line.split(",").map { it.trim() }
            // This section was to be enabled once suspend was working.
            delay(1000)

            // Gets the shipment and adds the update
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

            // Prints updates in console so you know that shipments are being tracked
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
