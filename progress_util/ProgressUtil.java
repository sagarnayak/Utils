package <YOUR PACKAGE HERE>;

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import com.met.atims.R


class ProgressUtil(private val context: Context) {
    private lateinit var dialog: Dialog
    private var count = 0

    fun isAnyThingInProgress(): Boolean {
        return count != 0
    }

    fun show(numberOfLoader: Int) {
        if (count != 0) {
            count += numberOfLoader
            return
        }

        dialog = Dialog(context, R.style.progressBarTheme)

        dialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        dialog.setContentView(R.layout.progress_layout)
        dialog.setCancelable(false)
        dialog.show()

        UiUtil.hideSoftKeyboard(context)

        count += numberOfLoader
    }

    fun hide() {
        count--
        if (count > 0) {
            return
        }

        hideForced()
    }

    fun hideForced() {
        @Suppress("SENSELESS_COMPARISON")
        if (this::dialog.isInitialized && dialog.isShowing)
            dialog.dismiss()

        count = 0
    }
}