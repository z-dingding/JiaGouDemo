package com.bc.demo.mvp.model;

import com.bc.demo.bean.Note;
import com.bc.demo.database.NoteBook_Dao;
import com.bc.demo.mvp.present.NoteBookP;

import java.util.ArrayList;

/**
 * Created by Ding on 2017/5/31.
 */
public class NoteBookMI implements NoteBookM {
    NoteBook_Dao dao ;
    ArrayList<Note> alit_notes;
    NoteBookP noteBookP;
   public NoteBookMI (NoteBookP noteBookP){
       this.noteBookP =noteBookP;
       dao=new NoteBook_Dao(noteBookP.getAppContext());
   }
    @Override
    public void onDestroy(boolean isChangingConfiguration) {
        if(!isChangingConfiguration){
            noteBookP = null;
            dao = null;
            alit_notes = null;
        }
    }

    /**
     * Model插入数据返回其下标即主键值
     * @param note
     * @return
     */
    @Override
    public int insertNote(Note note) {
        Note insertNote=dao.insertNote(note);
        loadData();
        if(insertNote != null){
           return  getNoteIndex(insertNote);
        }
        return -1;
    }

    @Override
    public boolean loadData() {
        alit_notes=dao.getAllData();
        return alit_notes != null;
    }

    @Override
    public Note getNote(int position) {
        return alit_notes.get(position);
    }

    @Override
    public boolean deleteNote(Note note, int adapterPos) {
          long id=dao.deleteNot(note);
        if(id > -1){  //这里注意
            alit_notes.remove(adapterPos);//从集合中移除
            return true;
        }else{
        return false;
    }
    }
    @Override
    public int getNotesCount() {
        if (alit_notes != null)
            return alit_notes.size();
        return 0;
    }

    @Override
    public boolean clearAllNotes() {
        dao.deleteAllData();
        loadData();
        if(alit_notes.size() == 0){
            return true;
        }else{
        return false;
        }
    }

    /**
     * 根据数据库插入数据返回其下标，在集合中的位置
     */


    public int getNoteIndex(Note note){
        for(int i=0;i<alit_notes.size();i++){
           if(note.getId() ==alit_notes.get(i).getId() ){
               return i;
           }
        }
        return -1;
    }
}
