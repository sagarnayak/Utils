package <YOUR PACKAGE HERE>;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;

/*
Created by Sagar Kumar Nayak
this is a class providing a basic progress loader to be injected to views.

dependencies -
your package resource.
layout for progress dialog given in enclosing folder.
*/
@SuppressWarnings("unused")
public class ProgressUtil {
    private Context context;
    private Dialog dialog;

    public ProgressUtil(Context context) {
        this.context = context;
    }

    public void show() {
        hide();
        dialog = new Dialog(context, R.style.progressBarTheme);
        //noinspection ConstantConditions
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.progress_layout);
        dialog.setCancelable(false);
        dialog.show();

        UiUtil.hideSoftKeyboard(context);
    }

    public void hide() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }
}
