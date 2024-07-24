interface BulkShipment {
    fun checkBulk(updatetime: Long, createdtime: Long): Boolean {
        if (updatetime - createdtime > 259200) {
            return true
        }
        return false
    }
}