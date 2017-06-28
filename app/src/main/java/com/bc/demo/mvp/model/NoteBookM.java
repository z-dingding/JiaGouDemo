package com.bc.demo.mvp.model;

import com.bc.demo.bean.Note;

/**
 * Created by Ding on 2017/5/31.
 */
public interface NoteBookM {
    void onDestroy(boolean isChangingConfiguration);
    int insertNote(Note note);
    boolean loadData();
    Note getNote(int position);
    boolean deleteNote(Note note, int adapterPos);
    int getNotesCount();
    boolean clearAllNotes();
}
