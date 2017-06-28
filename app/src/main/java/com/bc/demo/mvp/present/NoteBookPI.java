package com.bc.demo.mvp.present;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.bc.demo.R;
import com.bc.demo.bean.Note;
import com.bc.demo.mvp.model.NoteBookM;
import com.bc.demo.mvp.view.NoteBookActivity;
import com.bc.demo.mvp.view.NoteBookView;
import com.bc.demo.mvp.view.NotesViewHolder;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by Ding on 2017/5/31.
 */
public class NoteBookPI implements NoteBookP{
   WeakReference<NoteBookView> noteBookView;
   NoteBookM noteBookM;
    public NoteBookPI(NoteBookActivity NoteBookView) {
        this.noteBookView= new WeakReference<NoteBookView>((com.bc.demo.mvp.view.NoteBookView) NoteBookView);
    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {
        noteBookView =null;
        noteBookM.onDestroy(isChangingConfiguration);
        if(!isChangingConfiguration){
            noteBookM=null;
        }
    }



    private NoteBookView getView() throws NullPointerException {
        if (noteBookView != null) {
            return noteBookView.get();
        } else {
            throw new NullPointerException("");
        }
    }


    @Override
    public void setView(NoteBookView view) {

        noteBookView=new WeakReference<NoteBookView>(view);
    }

    public void setModel(NoteBookM noteBookM) {
        this.noteBookM = noteBookM;
        // start to load data
        loadData();
    }
    /**
     * Load data from Model in a AsyncTask
     */
    private void loadData() {
   new AsyncTask<Void,Void,Boolean>(){

       @Override
       protected Boolean doInBackground(Void... params) {
           return noteBookM.loadData();
       }

       @Override
       protected void onPostExecute(Boolean aBoolean) {
           if(aBoolean){ //success
               getView().notifyDataSetChanged();
           }else{//erroy
               getView().showSnackbar("加载失败了 ⊙﹏⊙");
           }
       }
   }.execute();
    }

//创建ViewHolder
    @Override
    public NotesViewHolder createViewHolder(ViewGroup parent, int viewType) {
        NotesViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewTaskRow = inflater.inflate(R.layout.holder_notes, parent, false);
        viewHolder = new NotesViewHolder(viewTaskRow);
        return viewHolder;
    }
//绑定数据
    @Override
    public void bindViewHolder(final NotesViewHolder holder, int position) {
        final Note note = noteBookM.getNote(position);
        holder.text.setText(note.getText());
        holder.date.setText(note.getDate());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getLayoutPosition()添加item的点击监听等功能
                clickDeleteNote(note, holder.getAdapterPosition(), holder.getLayoutPosition());
            }
        });
    }

    @Override
    public int getNotesCount() {
        return noteBookM.getNotesCount();
    }

    @Override
    public void clickNewNote(EditText editText) {
        final String noteText = editText.getText().toString();
        if (!noteText.isEmpty()) {
            new AsyncTask<Void, Void, Integer>() {
                @Override
                protected Integer doInBackground(Void... params) {
                    // Insert note in Model, returning result
                    return noteBookM.insertNote(makeNote(noteText));
                }

                @Override
                protected void onPostExecute(Integer adapterPosition) {
                    try {
                        if (adapterPosition > -1) {
                            // Insert note
                            getView().clearEditText();
                            //列表position位置添加一条数据时可以调用，伴有动画效果
                            getView().notifyItemInserted(adapterPosition + 1);
                            //列表从positionStart位置到itemCount数量的列表项进行数据刷新
                            getView().notifyItemRangeChanged(adapterPosition, noteBookM.getNotesCount());
                        } else {
                            // Inform about error
                            getView().showSnackbar("添加失败了 ⊙﹏⊙");
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }.execute();
        } else {
            try {
                getView().showSnackbar("请输点内容呗 ⊙﹏⊙");
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }



    public Note makeNote(String noteText) {
        Note note = new Note();
        note.setText(noteText);
        note.setDate(getDate());
        return note;

    }


    private String getDate() {
        return new SimpleDateFormat("HH:mm:ss - MM/dd/yyyy", Locale.getDefault()).format(new Date());
    }
    @Override
    public void clearAllNotes() {
           new AsyncTask<Void ,Void,Boolean>(){

               @Override
               protected Boolean doInBackground(Void... params) {
                   return noteBookM.clearAllNotes() ;
               }

               @Override
               protected void onPostExecute(Boolean aBoolean) {
                   if(aBoolean){
                       getView().notifyDataSetChanged();
                       getView().showSnackbar("已清空 ⊙﹏⊙");
                   }else{
                       getView().showSnackbar("...清空失败 ⊙﹏⊙");
                   }
               }
           }.execute();
    }

    @Override
    public void clickDeleteNote(Note note, int adapterPos, int layoutPos) {
        openDeleteAlert(note, adapterPos, layoutPos);
    }

    private void openDeleteAlert(final Note note, final int adapterPos, final int layoutPos) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivityContext());
        alertBuilder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteNote(note, adapterPos, layoutPos);
            }
        });
        alertBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertBuilder.setMessage("确定删除该条记录？");

        AlertDialog alertDialog = alertBuilder.create();
        try {
            getView().showAlert(alertDialog);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }
//定义删除单个数据的方法

    public void deleteNote(final Note note, final int adapterPos,final int layoutPos){
        new AsyncTask<Void,Void,Boolean>(){

            @Override
            protected Boolean doInBackground(Void... params) {
                return noteBookM.deleteNote(note,adapterPos) ;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                if(aBoolean){
                    getView().notifyItemRemoved(layoutPos);
                    getView().showSnackbar("已删除 0-0");
                }else{
                    getView().showSnackbar("...删除不了 ⊙﹏⊙");
                }

            }
        }.execute();
    }



    @Override
    public Context getAppContext() {
        return getView().getAppContext();
    }

    @Override
    public Context getActivityContext() {
        return getView().getActivityContext();
    }


}
