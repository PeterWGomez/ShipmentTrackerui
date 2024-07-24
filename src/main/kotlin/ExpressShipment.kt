interface ExpressShipment {
    fun checkExpress(updatetime: Long, createdtime: Long): Boolean {
        println("${updatetime} - ${createdtime} = ${updatetime - createdtime}")
        if (updatetime - createdtime < 259200000) {
            println("Here")
            return true
        }
        return false
    }
}