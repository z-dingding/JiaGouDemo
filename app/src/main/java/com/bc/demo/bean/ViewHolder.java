package com.bc.demo.bean;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Ding on 2017/5/22.
 */
public class ViewHolder {


    private SparseArray<View> mViews;
    private View mConvertView;



    private int mposition;
    private int mLayoutId;


    public ViewHolder(Context mContext, int mposition, int layoutId) {
        this.mposition = mposition;
        this.mLayoutId = layoutId;
        mConvertView=View.inflate(mContext,layoutId,null);
        mViews=new SparseArray<View>();
        //注意setTag
        mConvertView.setTag(this);
    }


    public static ViewHolder  getViewHolder(Context context,View mConvertView,int mposition, int layoutId){
        boolean isNewConverView =false;
        ViewHolder viewHolder = null;
        if(mConvertView == null){
            isNewConverView =true;
        }else{
            viewHolder= (ViewHolder) mConvertView.getTag();
        }
        //不太明白
        if(viewHolder != null && viewHolder.mLayoutId != layoutId){
            isNewConverView =true;
        }
        if(isNewConverView){
            //不太明白 mposition
            return new ViewHolder(context,mposition,layoutId);
        }
        return (ViewHolder) mConvertView.getTag();
    }


    /**　初始化控件 */
    public <T extends View> T getView( int resId){
        View view =mViews.get(resId);
        if(view == null){
            view =mConvertView.findViewById(resId);
            mViews.put(resId,view);
        }
        return (T) view;
    }


    /** 给控件赋值 */
    public void setText(int resId,String Content){
         if(!TextUtils.isEmpty(Content)){
           TextView textView=  getView(resId);
             if(textView != null){
                 textView.setText(Content);
             }
         }
    }



    public View getmConvertView() {
        return mConvertView;
    }

    public int getmLayoutId() {
        return mLayoutId;
    }

    public int getmPosition() {
        return mposition;
    }



}
