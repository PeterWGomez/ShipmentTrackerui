import org.example.Shipment
import org.example.ShippingUpdate
import org.junit.Assert.assertEquals
import org.junit.Test


//
class TrackingSimulatorTest {
    @Test
    fun testUpdateConstruction() {
        //Testing all 10 shipments being initialized
        var testTrack = TrackingSimulator()
        assertEquals("created", testTrack.shipments[0].status)
        assertEquals("s10000", testTrack.shipments[0].id)
        assertEquals("created", testTrack.shipments[1].status)
        assertEquals("s10001", testTrack.shipments[1].id)
        assertEquals("created", testTrack.shipments[2].status)
        assertEquals("s10002", testTrack.shipments[2].id)
        assertEquals("created", testTrack.shipments[3].status)
        assertEquals("s10003", testTrack.shipments[3].id)

        //Testing findShipment
        assertEquals("s10000", testTrack.findShipment("s10000")?.id)
        assertEquals("s10001", testTrack.findShipment("s10001")?.id)
        assertEquals("s10002", testTrack.findShipment("s10002")?.id)
        assertEquals("s10003", testTrack.findShipment("s10003")?.id)
        assertEquals(null, testTrack.findShipment("nothing"))

        //Testing addShipment
        var testshipment = Shipment("created", "s10010")
        testTrack.addShipment(testshipment)
        assertEquals("s10010", testTrack.findShipment("s10010")?.id)

    }
}