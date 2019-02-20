package <YOUR PACKAGE HERE>;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;

/*
To get the device id.
Created by Sagar Kumar Nayak
*/
@SuppressWarnings("unused")
public class DeviceUtil {
    @SuppressLint("HardwareIds")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

}
