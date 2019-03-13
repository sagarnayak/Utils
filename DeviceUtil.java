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

    public static String getVersionName(Context context) {
        PackageInfo pinfo = null;
        try {
            pinfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        int versionNumber = pinfo.versionCode;
        //noinspection UnnecessaryLocalVariable
        String versionName = pinfo.versionName;
        return versionName;
    }
}
