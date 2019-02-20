package <YOUR PACKAGE HERE>;

import android.content.Context;

import java.io.IOException;

import io.reactivex.annotations.NonNull;
import okhttp3.Interceptor;
import okhttp3.Request;

/*
Created By Sagar Kumar Nayak.
This is the offline cache interceptor used for okhttp.

Dependencies for this file

PROJECT LEVEL GRADLE -
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}

APP LEVEL GRADLE -
implementation 'com.github.sagarnayak:LogUtil:1.0.1'

NetworkUtil provided in the enclosing folder.
Const provided in enclosing folder.

if you dont want to print the log then you may remove it.

HOW TO USE -
to use this put this as interceptor in okhttp client.

return new OkHttpClient.Builder()
		..............
		.addInterceptor(offlineResponseCacheInterceptor)
		.cache(cache)
		.build();
*/
@SuppressWarnings("unused")
public class OfflineResponseCacheInterceptor implements Interceptor {
    private Context context;
    private LogUtil logUtil;

    public OfflineResponseCacheInterceptor(Context context, LogUtil logUtil) {
        this.context = context;
        this.logUtil = logUtil;
    }

    @Override
    public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        if (Boolean.valueOf(chain.request().header("cacheResponseOffline"))) {
            if (!NetworkUtil.isNetworkAvailable(context)) {
                logUtil.logV(Const.OFFLINE_CACHE_APPLIED);
                request = request.newBuilder()
                        .header("Cache-Control",
                                "public, only-if-cached, max-stale=" + 2419200)
                        .build();
            }
        } else {
            logUtil.logV(Const.OFFLINE_CACHE_NOT_APPLIED);
        }
        return chain.proceed(request);
    }
}