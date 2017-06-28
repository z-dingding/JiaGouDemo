package com.bc.demo.bean;

import com.bc.demo.R;

/**
 * Created by Ding on 2017/5/24.
 */
public enum  Search_ENUM {
    SEARCH(1, R.string.search);

    public int titleContentId;
    public int titleContent;





    public int getTitleContent() {
        return titleContent;
    }


    Search_ENUM(int titleContentId, int titleContent) {
        this.titleContentId = titleContentId;
        this.titleContent = titleContent;
    }

    public int getTitleContentId() {
        return titleContentId;
    }

    public static Search_ENUM getSearch_EnumBySelf(int id){
      for(Search_ENUM  s : values()){
          if(s.getTitleContentId() == id){
              return  s;
          }
      }
        return null;
    }
}
