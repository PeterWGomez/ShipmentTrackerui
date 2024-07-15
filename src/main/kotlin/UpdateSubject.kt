interface UpdateSubject {
    fun subscribe(observer: UpdateObserver)
    fun unsubscribe(observer: UpdateObserver)
    fun notifyObservers()
}