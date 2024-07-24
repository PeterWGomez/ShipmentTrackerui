interface BulkShipment {
    fun checkBulk(updatetime: Long, createdtime: Long): Boolean {
        if (updatetime - createdtime > 259200000) {
            return true
        }
        return false
    }
}