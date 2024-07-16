import org.example.Shipment
import org.example.ShippingUpdate
import org.junit.Assert.assertEquals
import org.junit.Test

class ShipmentTest {
    @Test
    fun testShipmentConstruction() {
        var shipment = Shipment("pending", "s10000")


        // Test fresh construction
        assertEquals("pending", shipment.status)
        assertEquals("s10000", shipment.id)

        //Testing addNote
        shipment.addNote("Test")
        assertEquals("Test", shipment.notes[0])

        //Testing addUpdate for created, shipped, delayed, lost, canceled, delivered
        var testUpdate = ShippingUpdate(shipment.status, "created", 1000L)
        shipment.addUpdate(testUpdate)
        assertEquals("pending", testUpdate.previousStatus)
        assertEquals(testUpdate.newStatus, shipment.status)
        assertEquals(testUpdate.timestamp, shipment.updateHistory[0].timestamp)

        testUpdate = ShippingUpdate(shipment.status, "shipped", 2000L)
        shipment.addUpdate(testUpdate)
        assertEquals("created", testUpdate.previousStatus)
        assertEquals(testUpdate.newStatus, shipment.status)
        assertEquals(testUpdate.timestamp, shipment.updateHistory[1].timestamp)

        testUpdate = ShippingUpdate(shipment.status, "delayed", 3000L)
        shipment.addUpdate(testUpdate)
        assertEquals("shipped", testUpdate.previousStatus)
        assertEquals(testUpdate.newStatus, shipment.status)
        assertEquals(testUpdate.timestamp, shipment.updateHistory[2].timestamp)

        testUpdate = ShippingUpdate(shipment.status, "lost", 4000L)
        shipment.addUpdate(testUpdate)
        assertEquals("delayed", testUpdate.previousStatus)
        assertEquals(testUpdate.newStatus, shipment.status)
        assertEquals(testUpdate.timestamp, shipment.updateHistory[3].timestamp)

        testUpdate = ShippingUpdate(shipment.status, "canceled", 5000L)
        shipment.addUpdate(testUpdate)
        assertEquals("lost", testUpdate.previousStatus)
        assertEquals(testUpdate.newStatus, shipment.status)
        assertEquals(testUpdate.timestamp, shipment.updateHistory[4].timestamp)

        testUpdate = ShippingUpdate(shipment.status, "delivered", 6000L)
        shipment.addUpdate(testUpdate)
        assertEquals("canceled", testUpdate.previousStatus)
        assertEquals(testUpdate.newStatus, shipment.status)
        assertEquals(testUpdate.timestamp, shipment.updateHistory[5].timestamp)

        //Testing update that is not the mentioned ones above, as it does not change the status
        testUpdate = ShippingUpdate(shipment.status, "invalid", 7000L)
        shipment.addUpdate(testUpdate)
        assertEquals("delivered", shipment.status)
        assertEquals(testUpdate.timestamp, shipment.updateHistory[6].timestamp)

    }
}