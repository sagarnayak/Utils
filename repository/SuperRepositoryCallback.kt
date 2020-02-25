package <YOUR PACKAGE HERE>;

import com.met.atims.util.model.Result

interface SuperRepositoryCallback<in T> {
    fun success(result: T) {}
    fun notAuthorised() {}
    fun noContent() {}
    fun error(result: Result) {}
}