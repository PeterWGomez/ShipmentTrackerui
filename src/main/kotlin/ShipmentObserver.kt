import org.example.Shipment

interface ShipmentObserver {
    fun notify(shipments: Shipment)
}