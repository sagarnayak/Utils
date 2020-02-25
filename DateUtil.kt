package <YOUR PACKAGE HERE>;

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@Suppress("unused")
object DateUtil {
    @SuppressLint("SimpleDateFormat")
    fun getDateToShowInUI(timeMills: Long): String {
        val cal = Calendar.getInstance()
        cal.timeInMillis = timeMills

        val simpleDateFormat =
            if (cal.get(Calendar.YEAR) > Calendar.getInstance().get(Calendar.YEAR))
                SimpleDateFormat("EEE',' dd MMM yyyy")
            else
                SimpleDateFormat("EEE',' dd MMM")
        return simpleDateFormat.format(cal.time)
    }


    @SuppressLint("SimpleDateFormat")
    fun getDateTimeToShowInUI(timeMills: Long): String {
        val cal = Calendar.getInstance()
        cal.timeInMillis = timeMills

        val simpleDateFormat =
            if (cal.get(Calendar.YEAR) > Calendar.getInstance().get(Calendar.YEAR))
                SimpleDateFormat("EEE',' dd MMM yyyy hh':'mm")
            else
                SimpleDateFormat("EEE',' dd MMM hh':'mm")
        return simpleDateFormat.format(cal.time)
    }

    @SuppressLint("SimpleDateFormat")
    fun getMillsFromServerTime(serverTimeString: String): Long? {
        val simpleDateFormatUTC = SimpleDateFormat("yyyy-MM-dd'T'hh':'mm':'ss'.'SSS'Z'")
        simpleDateFormatUTC.timeZone = TimeZone.getTimeZone("UTC")
        val simpleDateFormatLocal = SimpleDateFormat("yyyy-MM-dd'T'hh':'mm':'ss'.'SSS'Z'")
        simpleDateFormatLocal.timeZone = TimeZone.getDefault()
        val utcDate = simpleDateFormatUTC.parse(serverTimeString)
        utcDate?.let {
            val localString = simpleDateFormatLocal.format(it)
            val localDate = simpleDateFormatLocal.parse(localString)
            return localDate?.let { date ->
                return date.time
            } ?: run {
                return null
            }
        } ?: run {
            return null
        }

    }

    @SuppressLint("SimpleDateFormat")
    fun getTimeStringForServer(timeMills: Long): String {
        val cal = Calendar.getInstance()
        cal.timeInMillis = timeMills

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZZZZZ")
        return simpleDateFormat.format(cal.time)
    }
}