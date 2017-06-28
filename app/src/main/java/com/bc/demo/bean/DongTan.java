package com.bc.demo.bean;

import java.io.Serializable;

/**
 * Created by Ding on 2017/5/22.
 */
public class DongTan implements Serializable {
    String titlle="标题";
    String content="这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容";
    String time="2017/05/22";
    String author="dingDing";


    public DongTan(String titlle, String content, String time, String author) {
        this.titlle = titlle;
        this.content = content;
        this.time = time;
        this.author = author;
    }


    public String getTitlle() {
        return titlle;
    }

    public void setTitlle(String titlle) {
        this.titlle = titlle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
