package com.bc.demo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ding on 2017/5/31.
 */
public class NoteBook_Helper extends SQLiteOpenHelper {


    //数据库名和版本号
    private static final  String DB_NAME="notes.db";
    private static final int DB_VERSION = 1;

   //数据库表名

    public static final String DB_TableName="note";


    public static class Note{
        public static String Note_ID="_id";
        public static String Note_Content="content";
        public static String Note_Date="date";
    }



    //创建表的语句
    private static String CREATE_TABLE ="create table "+DB_TableName+"("+Note.Note_ID+" Integer not null primary key ,"+Note.Note_Content+" text not null ,"+Note.Note_Date+" datetime not null  )";


    //构造方法
    public NoteBook_Helper(Context context) {
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
           if(i < i1){
               sqLiteDatabase.execSQL(DB_TableName);
           }
    }
}
