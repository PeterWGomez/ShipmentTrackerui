import org.example.Shipment
import org.example.ShippingUpdate
import org.junit.Assert.assertEquals
import org.junit.Test

class ExpressTest {
    @Test
    fun testExpressConstruction() {
        var shipment = Shipment("pending", "s10000", shipmentType = "ExpressShipment", createdTimestamp = 0)
        // Test construction
        assertEquals("pending", shipment.status)
        assertEquals("s10000", shipment.id)
        assertEquals("ExpressShipment", shipment.shipmentType)

        // Test within timeframe
        var testUpdate = ShippingUpdate("previous", "shipped", 260200)
        shipment.addUpdate(testUpdate)
        shipment.addNote("")
        assertEquals("", shipment.notes[0])

        // Test outside of timeframe
        var shipment2 = Shipment("pending", "s10001", shipmentType = "ExpressShipment", createdTimestamp = 0)
        var testUpdate2 = ShippingUpdate("previous", "shipped", 259200000)
        shipment2.addUpdate(testUpdate2)
        assertEquals("WARNING: Express shipment will arrive late.", shipment2.notes[0])

        // Test delayed and out of timeframe
        var shipment3 = Shipment("pending", "s10002", shipmentType = "ExpressShipment", createdTimestamp = 0)
        var testUpdate3 = ShippingUpdate("previous", "delayed", 259200000)
        var testUpdate4 = ShippingUpdate("delayed", "shipped", 259200000)
        shipment3.addUpdate(testUpdate3)
        shipment3.addUpdate(testUpdate4)
        shipment3.addNote("")
        assertEquals("", shipment3.notes[0])
    }
}