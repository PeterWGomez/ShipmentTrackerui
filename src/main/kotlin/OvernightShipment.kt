interface OverShipment {
    fun checkOvernight(updatetime: Long, createdtime: Long): Boolean {
        if (updatetime - createdtime < 86400000) {
            return true
        }
        return false
    }
}