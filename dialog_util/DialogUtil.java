package <YOUR PACKAGE HERE>;

import android.app.Dialog;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/*
Created by Sagar Kumar Nayak
this is a class providing a few generic dialogs used in android apps.

dependencies -
your package resource.
layout for dialogs given in enclosing folder.
*/
@SuppressWarnings("unused")
public class DialogUtil {

    private static Dialog customDialog;

    public static void showDialogWithOneButton(
            Context context,
            String message,
            String buttonText,
            boolean cancellable,
            final DialogWithOneButtonCallBack dialogWithOneButtonCallBack
    ) {
        if (
                customDialog != null &&
                        customDialog.isShowing()
                )
            customDialog.dismiss();
        customDialog = new Dialog(context);
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setContentView(R.layout.dialog_with_single_button);
        //noinspection ConstantConditions
        customDialog.getWindow().setLayout(CoordinatorLayout.LayoutParams.MATCH_PARENT, CoordinatorLayout.LayoutParams.WRAP_CONTENT);
        Button buttonAction = customDialog.findViewById(R.id.button_action);
        TextView textViewMessage = customDialog.findViewById(R.id.text_view_message);
        buttonAction.setText(buttonText);
        textViewMessage.setText(message);
        customDialog.setCancelable(cancellable);
        buttonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog.dismiss();
                dialogWithOneButtonCallBack.buttonClicked();
            }
        });
        customDialog.setOnCancelListener(
                new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        dialogWithOneButtonCallBack.dialogCancelled();
                    }
                }
        );
        customDialog.show();
    }

    public interface DialogWithOneButtonCallBack {
        void dialogCancelled();

        void buttonClicked();
    }

    public static void showDialogWithTwoButton(
            Context context,
            String message,
            String buttonOneText,
            String buttonTwoText,
            boolean cancellable,
            final DialogWithTwoButtonCallBack dialogWithTwoButtonCallBack
    ) {
        if (
                customDialog != null &&
                        customDialog.isShowing()
                )
            customDialog.dismiss();
        customDialog = new Dialog(context);
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setContentView(R.layout.dialog_with_double_button);
        //noinspection ConstantConditions
        customDialog.getWindow().setLayout(CoordinatorLayout.LayoutParams.MATCH_PARENT, CoordinatorLayout.LayoutParams.WRAP_CONTENT);
        Button buttonActionOne = customDialog.findViewById(R.id.button_action_one);
        Button buttonActionTwo = customDialog.findViewById(R.id.button_action_two);
        TextView textViewMessage = customDialog.findViewById(R.id.text_view_message);
        buttonActionOne.setText(buttonOneText);
        buttonActionTwo.setText(buttonTwoText);
        textViewMessage.setText(message);
        customDialog.setCancelable(cancellable);
        buttonActionOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog.dismiss();
                dialogWithTwoButtonCallBack.buttonOneClicked();
            }
        });
        buttonActionTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog.dismiss();
                dialogWithTwoButtonCallBack.buttonTwoClicked();
            }
        });
        customDialog.setOnCancelListener(
                new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        dialogWithTwoButtonCallBack.dialogCancelled();
                    }
                }
        );
        customDialog.show();
    }

    public interface DialogWithTwoButtonCallBack {
        void dialogCancelled();

        void buttonOneClicked();

        void buttonTwoClicked();
    }

    public static void showDialogWithMessage(
            Context context,
            String message
    ) {
        if (
                customDialog != null &&
                        customDialog.isShowing()
                )
            customDialog.dismiss();
        customDialog = new Dialog(context);
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setContentView(R.layout.dialog_with_single_button);
        //noinspection ConstantConditions
        customDialog.getWindow().setLayout(CoordinatorLayout.LayoutParams.MATCH_PARENT, CoordinatorLayout.LayoutParams.WRAP_CONTENT);
        Button buttonAction = customDialog.findViewById(R.id.button_action);
        TextView textViewMessage = customDialog.findViewById(R.id.text_view_message);
        buttonAction.setText("OK");
        textViewMessage.setText(message);
        customDialog.setCancelable(true);
        buttonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog.dismiss();
            }
        });
        customDialog.setOnCancelListener(
                new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                    }
                }
        );
        customDialog.show();
    }
}

