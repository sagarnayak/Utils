package <YOUR PACKAGE HERE>;

import android.content.Context
import android.content.SharedPreferences

/*
this file can be put into a seperate di package instead of keeping in util
*/
class SharedPrefModule(context: Context) {

    var pref: SharedPreferences = context.getSharedPreferences(
        SHARED_PREF_DB,
        Context.MODE_PRIVATE
    )
}