package <YOUR PACKAGE HERE>;

object NumberUtil {
    fun roundToTwoDecimal(number: Float): String {
        return "%.0f".format(number)
    }
}