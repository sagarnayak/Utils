package <YOUR PACKAGE HERE>;

import com.collanomics.android.catchthemyoung.student.catchthemyoung.util.network.Const;
import com.sagar.android.logutilmaster.LogUtil;

import java.io.IOException;

import io.reactivex.annotations.NonNull;
import okhttp3.Interceptor;

/*
Created By Sagar Kumar Nayak.
This is the response cache interceptor used for okhttp.

Dependencies for this file

PROJECT LEVEL GRADLE -
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}

APP LEVEL GRADLE -
implementation 'com.github.sagarnayak:LogUtil:1.0.1'

Const provided in enclosing folder.

if you dont want to print the log then you may remove it.

HOW TO USE -
to use this put this as interceptor in okhttp client.

return new OkHttpClient.Builder()
		..............
		.addInterceptor(responseCacheInterceptor)
		.cache(cache)
		.build();
*/
@SuppressWarnings("unused")
public class ResponseCacheInterceptor implements Interceptor {
    private LogUtil logUtil;

    public ResponseCacheInterceptor(LogUtil logUtil) {
        this.logUtil = logUtil;
    }

    @Override
    public okhttp3.Response intercept(@SuppressWarnings("NullableProblems") @NonNull Chain chain)
            throws IOException {
        okhttp3.Response originalResponse = chain.proceed(chain.request());
        if (Boolean.valueOf(chain.request().header("cacheResponse"))) {
            logUtil.logV(Const.CACHE_APPLIED);
            originalResponse = originalResponse.newBuilder()
                    .header("Cache-Control", "public, max-age=" + 600)
                    .build();
        } else {
            logUtil.logV(Const.CACHE_NOT_APPLIED);
        }
        return originalResponse;
    }
}
