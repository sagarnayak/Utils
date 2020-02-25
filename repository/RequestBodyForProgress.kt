package <YOUR PACKAGE HERE>;

import android.os.Handler
import android.os.Looper
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.*
import java.io.IOException

open class RequestBodyForProgress(
    private val delegate: RequestBody,
    private val progressCallback: ProgressCallback,
    private val debounceMills: Long = 1000,
    private val identifier: String
) : RequestBody() {

    private lateinit var countingSink: CountingSink

    override fun contentType(): MediaType? {
        return delegate.contentType()
    }

    override fun contentLength(): Long {
        try {
            return delegate.contentLength()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return -1
    }

    @Throws(IOException::class)
    override fun writeTo(sink: BufferedSink) {
        countingSink = CountingSink(sink)
        val bufferedSink: BufferedSink = countingSink.buffer()

        delegate.writeTo(bufferedSink)

        bufferedSink.flush()
    }

    protected inner class CountingSink(delegate: Sink) : ForwardingSink(delegate) {

        private var bytesWritten: Long = 0

        @Throws(IOException::class)
        override fun write(source: Buffer, byteCount: Long) {
            super.write(source, byteCount)

            bytesWritten += byteCount
            newProgressUpdate(bytesWritten)
        }
    }

    interface ProgressCallback {
        fun progressChange(progress: Int, identifier: String)
    }

    private var allowedToPostUpdate = true

    private fun newProgressUpdate(done: Long) {
        val progressDone = ((done.toFloat() / contentLength()) * 100).toInt()
        if (allowedToPostUpdate || progressDone == 100) {
            blockProgressUpdate()
            progressCallback.progressChange(progressDone, identifier)
        }
    }

    private fun blockProgressUpdate() {
        allowedToPostUpdate = false
        Handler(Looper.getMainLooper()).postDelayed(
            {
                allowedToPostUpdate = true
            },
            debounceMills
        )
    }
}