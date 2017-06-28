package com.bc.demo.mvp.view;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Created by Ding on 2017/5/31.
 */
public interface NoteBookView {

    Context getAppContext();
    Context getActivityContext();
    void showSnackbar( String msg);
    void showAlert(AlertDialog dialog);
    void notifyItemRemoved(int position);
    void notifyDataSetChanged();
    void notifyItemInserted(int layoutPosition);
    void notifyItemRangeChanged(int positionStart, int itemCount);
    void clearEditText();
}
