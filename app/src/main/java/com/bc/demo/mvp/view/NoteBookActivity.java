package com.bc.demo.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bc.demo.AppManager;
import com.bc.demo.R;
import com.bc.demo.base.BaseActivity;
import com.bc.demo.mvp.model.NoteBookMI;
import com.bc.demo.mvp.present.NoteBookP;
import com.bc.demo.mvp.present.NoteBookPI;
import com.bc.demo.utils.StateMaintainer;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ding on 2017/5/31.
 */
public class NoteBookActivity extends BaseActivity implements NoteBookView {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.edit_note)
    EditText editNote;
    @Bind(R.id.recyclerView_notes)
    RecyclerView recyclerViewNotes;
    @Bind(R.id.main_cotent_rl)
    RelativeLayout mainCotentRl;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.notebook_title)
    TextView tv_title;

     NoteBookP noteBookP;//presenter接口
    ListNotes mListAdapter;//适配器
    private final StateMaintainer mStateMaintainer =
            new StateMaintainer(getFragmentManager(), NoteBookActivity.class.getName());
    @Override
    protected int setLayoutId() {
        return R.layout.activity_notebook;
    }

    @Override
    public void initView() {
        toolbar.setNavigationIcon(R.mipmap.image_default_back);
        toolbar.setTitle("");
        tv_title.setText("备忘录");
        setSupportActionBar(toolbar);
    }

    @Override
    public void initData() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteBookP.clickNewNote(editNote);
            }
        });
        mListAdapter = new ListNotes();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewNotes.setLayoutManager(linearLayoutManager);
        recyclerViewNotes.setAdapter(mListAdapter);
        recyclerViewNotes.setItemAnimator(new DefaultItemAnimator());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notebook_toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                AppManager.getInstance().finishActivity(this);
                break;

            case R.id.notebook_clearall:
                noteBookP.clearAllNotes();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    /** 该方法会自动传入一个 Bundle 对象,
     * 该 Bundle 对象就是上次被系统销毁时在 onSaveInstanceState
     * 或者 onRestoreInstanceState 中保存的数据;
     * 只有是系统自动回收的时候才会保存 Bundle 对象数据;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        initMVP();
    }

    private void initMVP() {
        if (mStateMaintainer.firstTimeIn()) {
            //构造方法初始了view也就是MainActivity。
            NoteBookPI presenter = new NoteBookPI(this);
            //初始化了MainPresenter和dao
            NoteBookMI model = new NoteBookMI(presenter);
            //设置了ProvidedModel
            presenter.setModel(model);
            mStateMaintainer.put(presenter);
            mStateMaintainer.put(model);
            noteBookP = presenter;
        } else {
            noteBookP = mStateMaintainer.get(NoteBookP.class.getName());
            noteBookP.setView(this);
        }
    }


    @Override
    public Context getAppContext() {
        return this.getActivityContext();
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public void showSnackbar(String msg) {
        Snackbar.make(mainCotentRl,msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showAlert(AlertDialog dialog) {
        dialog.show();
    }

    @Override
    public void notifyItemRemoved(int position) {
        mListAdapter.notifyItemRemoved(position);
    }

    @Override
    public void notifyDataSetChanged() {
        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyItemInserted(int layoutPosition) {
        mListAdapter.notifyItemInserted(layoutPosition);
    }

    @Override
    public void notifyItemRangeChanged(int positionStart, int itemCount) {
        mListAdapter.notifyItemRangeChanged(positionStart, itemCount);
    }

    @Override
    public void clearEditText() {
        editNote.setText("");
    }


    /** 适配器*/
    private class ListNotes extends RecyclerView.Adapter<NotesViewHolder> {


        @Override
        public int getItemCount() {
            return noteBookP.getNotesCount();
        }

        @Override
        public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return noteBookP.createViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(NotesViewHolder holder, int position) {
            noteBookP.bindViewHolder(holder, position);
        }
    }




}
