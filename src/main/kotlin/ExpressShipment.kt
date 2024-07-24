interface ExpressShipment {
    fun checkExpress(updatetime: Long, createdtime: Long): Boolean {
        if (updatetime - createdtime < 259200) {
            return true
        }
        return false
    }
}