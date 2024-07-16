import org.example.ShippingUpdate
import org.junit.Assert.assertEquals
import org.junit.Test

class ShippingUpdateTest {
    @Test
    fun testUpdateConstruction() {
        var testUpdate = ShippingUpdate("previous", "new", 1000L)
        // Test construction and public getter
        assertEquals("previous", testUpdate.previousStatus)
        assertEquals("new", testUpdate.newStatus)
        assertEquals(1000L, testUpdate.timestamp)

        // Test public setter
        testUpdate.previousStatus = "previous2"
        testUpdate.newStatus = "new2"
        testUpdate.timestamp= 2000L
        assertEquals("previous2", testUpdate.previousStatus)
        assertEquals("new2", testUpdate.newStatus)
        assertEquals(2000L, testUpdate.timestamp)
    }
}