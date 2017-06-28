package com.bc.demo.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bc.demo.bean.Note;

import java.util.ArrayList;

/**
 * Created by Ding on 2017/5/31.
 */
public class NoteBook_Dao {
    NoteBook_Helper helper;



    public NoteBook_Dao(Context context) {
        helper=new NoteBook_Helper(context);
    }
    //常用的sql语句
   //根据ID查询
    private   static final String TABLE_ID=NoteBook_Helper.Note.Note_ID+"=?";
    //按照 id 降序排列,desc ；
    public static final String SORT_ORDER_DEFAULT = NoteBook_Helper.Note.Note_ID+" DESC";


    //得到数据库读的对象
    public SQLiteDatabase  getReaderDatabase(){
        return helper.getReadableDatabase();
    }
    //得到数据库读写的对象
    public SQLiteDatabase  getWriterDatabase(){
        return helper.getWritableDatabase();
    }


    /**
     * 插入数据
     * @param note
     * @return note对象
     */
   public Note insertNote(Note note){
       SQLiteDatabase db=getWriterDatabase();
       //已经插入成功
       long id=db.insert( NoteBook_Helper.DB_TableName,null,note.getValues());
       Note  insertNote = getNote((int) id);
       db.close();
       return insertNote;
   }

    /**
     * 删除数据
      * @param note
     * @return
     */

    public Long deleteNot(Note note){
        SQLiteDatabase db=getWriterDatabase();
    long id=db.delete(NoteBook_Helper.DB_TableName,TABLE_ID,new String[]{Integer.toString(note.getId())});
        db.close();
        return id;
    }

    /**
     * 清空所用数据
     */
    public void  deleteAllData(){
        SQLiteDatabase db=getWriterDatabase();
        db.execSQL("delete from " + NoteBook_Helper.DB_TableName);//注意from后有空格
        db.close();
    }


    /**
     * 查询所有数据，返回集合
     *
     * @return ArraList;
     */
    public ArrayList<Note> getAllData(){
        SQLiteDatabase db=getReaderDatabase();
        ArrayList<Note>  alist_notes=new ArrayList<Note>();
        Cursor cursor =db.query(NoteBook_Helper.DB_TableName,null,null,null,null,null,SORT_ORDER_DEFAULT);
        if(cursor != null){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                Note note =new Note();
                note.setId(cursor.getInt(cursor.getColumnIndexOrThrow(NoteBook_Helper.Note.Note_ID)));
                note.setText(cursor.getString(cursor.getColumnIndexOrThrow(NoteBook_Helper.Note.Note_Content)));
                note.setDate(cursor.getString(cursor.getColumnIndexOrThrow(NoteBook_Helper.Note.Note_Date)));
                alist_notes.add(note);
                cursor.moveToNext();

            }

            cursor.close();
            db.close();
            return alist_notes;//注意先关闭，后返回
        }
        return null;
    }

    /**
     *
     * 根据id得到Note对象
     * @param id
     * @return Note
     */

    public Note getNote(int id){
        SQLiteDatabase db=getReaderDatabase();
        Cursor cursor =db.query(NoteBook_Helper.DB_TableName,null,TABLE_ID,new String[]{Integer.toString(id)},null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
           Note note =new Note();
            note.setId(cursor.getInt(cursor.getColumnIndexOrThrow(NoteBook_Helper.Note.Note_ID)));
            note.setText(cursor.getString(cursor.getColumnIndexOrThrow(NoteBook_Helper.Note.Note_Content)));
            note.setDate(cursor.getString(cursor.getColumnIndexOrThrow(NoteBook_Helper.Note.Note_Date)));
            cursor.close();
            db.close();
            return note;
        }
        return null;
    }







}
