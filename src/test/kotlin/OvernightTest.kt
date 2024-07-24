import org.example.Shipment
import org.example.ShippingUpdate
import org.junit.Assert.assertEquals
import org.junit.Test

class OvernightTest {
    @Test
    fun testOvernightConstruction() {
        var shipment = Shipment("pending", "s10000", shipmentType = "OvernightShipment", createdTimestamp = 1652712855468)
        // Test construction
        assertEquals("pending", shipment.status)
        assertEquals("s10000", shipment.id)
        assertEquals("OvernightShipment", shipment.shipmentType)

        // Test within timeframe
        var testUpdate = ShippingUpdate("previous", "shipped", 1652712955469)
        shipment.addUpdate(testUpdate)
        shipment.addNote("")
        assertEquals("", shipment.notes[0])

        // Test outside of timeframe
        var shipment2 = Shipment("pending", "s10001", shipmentType = "OvernightShipment", createdTimestamp = 1652712855468)
        var testUpdate2 = ShippingUpdate("previous", "shipped", 9999999999999)
        shipment2.addUpdate(testUpdate2)
        assertEquals("WARNING: Overnight shipment will arrive late.", shipment2.notes[0])

        // Test delayed and out of timeframe
        var shipment3 = Shipment("pending", "s10002", shipmentType = "OvernightShipment", createdTimestamp = 1652712855468)
        var testUpdate3 = ShippingUpdate("previous", "delayed", 9999999999999)
        var testUpdate4 = ShippingUpdate("delayed", "shipped", 9999999999999)
        shipment3.addUpdate(testUpdate3)
        shipment3.addUpdate(testUpdate4)
        shipment3.addNote("")
        assertEquals("", shipment3.notes[0])
    }
}