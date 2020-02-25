package <YOUR PACKAGE HERE>;

interface SuperRepositoryCallback<in T> {
    fun success(result: T) {}
    fun notAuthorised() {}
    fun noContent() {}
    fun error(result: Result) {}
}