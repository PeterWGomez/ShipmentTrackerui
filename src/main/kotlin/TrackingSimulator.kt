import org.example.Shipment
import org.example.shippingUpdate

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
    // Run Simulation starts the timer for each shipment and starts pulling updates from the test.txt
    fun runSimulation() {

    }
}
