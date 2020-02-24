package com.example.piets.hockeymanagement.Classes;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.piets.hockeymanagement.Coach.CoachMenu_Activity;
import com.example.piets.hockeymanagement.Coach.MatchStats.MainActivity;
import com.example.piets.hockeymanagement.Login_Activity;
import com.example.piets.hockeymanagement.R;
import com.example.piets.hockeymanagement.Room.MatchStats;

import static com.example.piets.hockeymanagement.R.mipmap.ic_logo_foreground;

//Helper Class to re-use methods in project.

public class Helper extends AppCompatActivity
{
    @NonNull
    public static String getText(EditText editText)
    {
        return editText.getText().toString().trim();
    }

    @NonNull
    public static String getTextViewText(TextView textView)
    {
        return textView.getText().toString().trim();
    }

    public static void clearText(View... views) {
        for (View item : views) {
            if (item instanceof EditText) {
                ((EditText) item).setText("");
            }
        }
    }

    public static int getIndexSpinner(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }

    public static boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;
        return true;
    }

    public static AlertDialog saveMatchStats(final MatchStats matchStats,Context context)
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(context)
                //set message, title, and icon
                .setTitle("Save Match Statistics")
                .setMessage("Are You Sure You Want To Save This Statistics?")
                .setIcon(R.drawable.ic_save)

                .setPositiveButton("SAVE", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton)
                    {

                        MainActivity.matchStatsDataBase.matchStatsDao().update(matchStats);
                        dialog.dismiss();
                    }

                })



                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();
        return myQuittingDialogBox;

    }

    public static void customToastA(Activity context,String msg)
    {
        View toastView = context.getLayoutInflater().inflate(R.layout.custom_toast,(ViewGroup) context.findViewById(R.id.customToast));

        TextView textView = (TextView) toastView.findViewById(R.id.tv_custom_toast);
        textView.setText(msg);

        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(toastView);
        toast.setGravity(Gravity.BOTTOM|Gravity.FILL_HORIZONTAL,0,0);
        toast.show();
    }


}
