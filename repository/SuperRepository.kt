package <YOUR PACKAGE HERE>;

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.met.atims.util.model.Result
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

@Suppress("unused")
open class SuperRepository {

    //util function
    fun getErrorMessage(throwable: Throwable): String {
        return if (throwable is HttpException) {
            val responseBody = throwable.response()!!.errorBody()
            try {
                val jsonObject = JSONObject(responseBody!!.string())
                jsonObject.getString("error")
            } catch (e: Exception) {
                e.message!!
            }
        } else (when (throwable) {
            is SocketTimeoutException -> "Timeout occurred"
            is IOException -> "network error"
            else -> throwable.message
        })!!
    }

    fun getErrorMessage(responseBody: ResponseBody): String {
        return try {
            val jsonObject = JSONObject(responseBody.string())
            jsonObject.getString("error")
        } catch (e: Exception) {
            "Something went wrong."
        }
    }

    inline fun <reified T> fromJson(json: String): T {
        return Gson().fromJson(json, object : TypeToken<T>() {}.type)
    }

    fun toJson(argument: Any) = Gson().toJson(argument)!!

    inline fun <reified T> makeApiCall(
        observable: Observable<Response<ResponseBody>>,
        callback: SuperRepositoryCallback<T>? = null,
        successMutableLiveData: MutableLiveData<Event<T>>? = null,
        errorMutableLiveData: MutableLiveData<Event<Result>>? = null,
        shouldReadOnce: Boolean = true
    ) {
        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : Observer<Response<ResponseBody>> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: Response<ResponseBody>) {
                        when (t.code()) {
                            StatusCode.OK.code, StatusCode.Created.code -> {
                                t.body()?.let {
                                    val reply = fromJson<T>(it.string())
                                    successMutableLiveData?.postValue(
                                        Event(
                                            reply,
                                            shouldReadOnce
                                        )
                                    )
                                    callback?.success(reply)
                                    "success"
                                } ?: run {
                                    val errorResult = Result(
                                        result = ResultType.FAIL
                                    )
                                    errorMutableLiveData?.postValue(
                                        Event(
                                            errorResult
                                        )
                                    )
                                    callback?.error(errorResult)
                                }
                            }
                            StatusCode.Unauthorized.code -> {
                                callback?.notAuthorised()
                                if (this@SuperRepository::superRepositoryUnAuthorisedCallbackGlobal.isInitialized)
                                    superRepositoryUnAuthorisedCallbackGlobal.notAuthorised()
                            }
                            StatusCode.NoContent.code -> {
                                callback?.noContent()
                                errorMutableLiveData?.postValue(
                                    Event(
                                        Result(
                                            error = "No content to show",
                                            result = ResultType.FAIL,
                                            responseCode = StatusCode.NoContent.code
                                        )
                                    )
                                )
                            }
                            else -> {
                                t.errorBody()?.let {
                                    val error = fromJson<Result>(it.string())
                                    val errorResult = Result(
                                        result = ResultType.FAIL,
                                        error = error.getMessageToShow()
                                    )
                                    errorMutableLiveData?.postValue(
                                        Event(
                                            errorResult
                                        )
                                    )

                                    callback?.error(
                                        errorResult
                                    )
                                    "success"
                                } ?: run {
                                    val errorResult = Result(
                                        result = ResultType.FAIL
                                    )
                                    errorMutableLiveData?.postValue(
                                        Event(
                                            errorResult
                                        )
                                    )

                                    callback?.error(
                                        errorResult
                                    )
                                }
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                        val errorResult = Result(
                            error = getErrorMessage(e),
                            result = ResultType.FAIL
                        )
                        errorMutableLiveData?.postValue(
                            Event(
                                errorResult
                            )
                        )

                        callback?.error(errorResult)
                    }
                }
            )
    }

    lateinit var superRepositoryUnAuthorisedCallbackGlobal: SuperRepositoryCallback<Result>

    fun registerForUnAuthorisedGlobalCallback(callback: SuperRepositoryCallback<Result>) {
        this.superRepositoryUnAuthorisedCallbackGlobal = callback
    }
}