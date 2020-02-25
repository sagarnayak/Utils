@file:Suppress("unused")

package <YOUR PACKAGE HERE>;

import androidx.lifecycle.ViewModel

open class SuperViewModel(private val repository: Repository) : ViewModel() {

    private var activityPaused = false
    private var shouldNotifyWhileActivityPaused = true

    fun notifyDuringPaused(shouldNotifyWhileActivityPaused: Boolean) {
        this.shouldNotifyWhileActivityPaused = shouldNotifyWhileActivityPaused
    }

    fun activityPaused() {
        activityPaused = true
    }

    fun activityResumed() {
        activityPaused = false
    }

    fun canPushData(): Boolean {
        if (shouldNotifyWhileActivityPaused)
            return true
        return !activityPaused
    }

    fun giveRepository(): Repository {
        return repository
    }
}