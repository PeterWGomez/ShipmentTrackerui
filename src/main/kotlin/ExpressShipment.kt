interface ExpressShipment {
    fun checkExpress(updatetime: Long, createdtime: Long): Boolean {
        if (updatetime - createdtime < 259200000) {
            println("Here")
            return true
        }
        return false
    }
}