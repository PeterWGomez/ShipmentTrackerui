import org.example.Shipment
import org.example.ShippingUpdate
import org.junit.Assert.assertEquals
import org.junit.Test

class BulkTest {
    @Test
    fun testBulkConstruction() {
        var shipment = Shipment("pending", "s10000", shipmentType = "BulkShipment", createdTimestamp = 1652712855468)
        // Test construction
        assertEquals("pending", shipment.status)
        assertEquals("s10000", shipment.id)
        assertEquals("BulkShipment", shipment.shipmentType)

        // Test within timeframe
        var testUpdate = ShippingUpdate("previous", "shipped", 9999999999999)
        shipment.addUpdate(testUpdate)
        shipment.addNote("")
        assertEquals("", shipment.notes[0])

        // Test outside of timeframe
        var shipment2 = Shipment("pending", "s10001", shipmentType = "BulkShipment", createdTimestamp = 1652712855468)
        var testUpdate2 = ShippingUpdate("previous", "shipped", 1652712955469)
        shipment2.addUpdate(testUpdate2)
        assertEquals("WARNING: Bulk shipment will arrive early.", shipment2.notes[0])

        // Test delayed and out of timeframe
        var shipment3 = Shipment("pending", "s10002", shipmentType = "BulkShipment", createdTimestamp = 1652712855468)
        var testUpdate3 = ShippingUpdate("previous", "delayed", 1652712955469)
        var testUpdate4 = ShippingUpdate("delayed", "shipped", 1652712955469)
        shipment3.addUpdate(testUpdate3)
        shipment3.addUpdate(testUpdate4)
        shipment3.addNote("")
        assertEquals("", shipment3.notes[0])
    }
}