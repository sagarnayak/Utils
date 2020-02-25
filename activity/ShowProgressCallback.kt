package <YOUR PACKAGE HERE>;

@Suppress("unused")
interface ShowProgressCallback {
    fun showProgress(numberOfLoader: Int = 1)
    fun hideProgress()
    fun isAnyThingInProgress(): Boolean
}