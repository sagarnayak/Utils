package <YOUR PACKAGE HERE>;

import android.content.Context
import android.content.SharedPreferences
import com.met.atims.core.KeyWordsAndConstants.SHARED_PREF_DB

class SharedPrefModule(context: Context) {

    var pref: SharedPreferences = context.getSharedPreferences(
        SHARED_PREF_DB,
        Context.MODE_PRIVATE
    )
}