package <YOUR PACKAGE HERE>;

import android.graphics.drawable.Drawable

@Suppress("unused")
interface ShowMessageCallback {
    fun showMessageInDialog(message: String)

    fun showMessageWithOneButton(
        message: String,
        callback: DialogUtil.CallBack,
        cancellable: Boolean = false,
        buttonText: String = "Ok",
        image: Drawable? = null
    )

    fun showMessageWithTwoButton(
        message: String,
        callback: DialogUtil.MultiButtonCallBack,
        cancellable: Boolean = false,
        buttonOneText: String = "Ok",
        buttonTwoText: String = "Cancel",
        image: Drawable? = null
    )
}