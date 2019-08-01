package <YOUR PACKAGE HERE>;

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable


class ProgressUtil(private val context: Context) {
    private lateinit var dialog: Dialog

    fun show() {
        hide()
        dialog = Dialog(context, R.style.progressBarTheme)

        dialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        dialog.setContentView(R.layout.progress_layout)
        dialog.setCancelable(false)
        dialog.show()

        UiUtil.hideSoftKeyboard(context)
    }

    fun hide() {
        @Suppress("SENSELESS_COMPARISON")
        if (dialog != null && dialog.isShowing)
            dialog.dismiss()
    }
}