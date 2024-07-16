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
        assertEquals("pending", testTrack.shipments[0].status)
        assertEquals("s10000", testTrack.shipments[0].id)
        assertEquals("pending", testTrack.shipments[1].status)
        assertEquals("s10001", testTrack.shipments[1].id)
        assertEquals("pending", testTrack.shipments[2].status)
        assertEquals("s10002", testTrack.shipments[2].id)
        assertEquals("pending", testTrack.shipments[3].status)
        assertEquals("s10003", testTrack.shipments[3].id)
        assertEquals("pending", testTrack.shipments[4].status)
        assertEquals("s10004", testTrack.shipments[4].id)
        assertEquals("pending", testTrack.shipments[5].status)
        assertEquals("s10005", testTrack.shipments[5].id)
        assertEquals("pending", testTrack.shipments[6].status)
        assertEquals("s10006", testTrack.shipments[6].id)
        assertEquals("pending", testTrack.shipments[7].status)
        assertEquals("s10007", testTrack.shipments[7].id)
        assertEquals("pending", testTrack.shipments[8].status)
        assertEquals("s10008", testTrack.shipments[8].id)
        assertEquals("pending", testTrack.shipments[9].status)
        assertEquals("s10009", testTrack.shipments[9].id)

        //Testing findShipment
        assertEquals("s10000", testTrack.findShipment("s10000")?.id)
        assertEquals("s10001", testTrack.findShipment("s10001")?.id)
        assertEquals("s10002", testTrack.findShipment("s10002")?.id)
        assertEquals("s10003", testTrack.findShipment("s10003")?.id)
        assertEquals("s10004", testTrack.findShipment("s10004")?.id)
        assertEquals("s10005", testTrack.findShipment("s10005")?.id)
        assertEquals("s10006", testTrack.findShipment("s10006")?.id)
        assertEquals("s10007", testTrack.findShipment("s10007")?.id)
        assertEquals("s10008", testTrack.findShipment("s10008")?.id)
        assertEquals("s10009", testTrack.findShipment("s10009")?.id)
        assertEquals(null, testTrack.findShipment("nothing"))

        //Testing addShipment
        var testshipment = Shipment("pending", "s10010")
        testTrack.addShipment(testshipment)
        assertEquals("s10010", testTrack.findShipment("s10010")?.id)

    }
}