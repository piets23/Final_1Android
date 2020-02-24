package com.example.piets.hockeymanagement.Classes;

import android.app.Fragment;
import android.app.ProgressDialog;

import android.support.annotation.VisibleForTesting;

/**
 * Created by piets on 2018/10/14.
 */

public class FragmentBase extends android.support.v4.app.Fragment
{
    @VisibleForTesting
    public ProgressDialog mProgressDialog;

    public void showProgressDialog(String message) {
        //if (mProgressDialog == null) {
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage(message);
        mProgressDialog.setIndeterminate(true);
        // }

        mProgressDialog.show();
    }


    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }
}
