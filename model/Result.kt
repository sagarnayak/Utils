package <YOUR PACKAGE HERE>;

data class Result(
    private var message: String = "",
    private var result: ResultType,
    private var error: String = "",
    private var responseCode: Int = StatusCode.OK.code
) {
    @Suppress("unused")
    fun isResultOk(): Boolean {
        @Suppress("SENSELESS_COMPARISON")
        if (result == null)
            result =
                if (error == "" || error == null) ResultType.OK else ResultType.FAIL

        return result == ResultType.OK
    }

    @Suppress("unused")
    fun getMessageToShow(): String {
        return if (isResultOk())
            message
        else error
    }

    @Suppress("unused")
    fun getCode() = responseCode
}