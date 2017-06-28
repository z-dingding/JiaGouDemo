package com.bc.demo.mvp.present;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.EditText;

import com.bc.demo.bean.Note;
import com.bc.demo.mvp.view.NoteBookView;
import com.bc.demo.mvp.view.NotesViewHolder;

/**
 * Created by Ding on 2017/5/31.
 */
public interface NoteBookP {
    void onDestroy(boolean isChangingConfiguration);

    void setView(NoteBookView view);

    NotesViewHolder createViewHolder(ViewGroup parent, int viewType);

    void bindViewHolder(NotesViewHolder holder, int position);

    int getNotesCount();

    void clickNewNote(EditText editText);

    void clearAllNotes();

    void clickDeleteNote(Note note, int adapterPos, int layoutPos);

    Context getAppContext();

    Context getActivityContext();
}
