package com.laojiang.androidlearn70.utils.weight.adapterpacking;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 封装BaseAdapter
 * Created by Jiang on 2016/12/27.
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter {
    public Context context;
    public List<T> list;
    public String[] str;
   private String  listOrStr="";
    private int RFiles;



    public   MyBaseAdapter(Context context, List<T> list){
    this.context = context;
    this.list = list;
   listOrStr = "list";
}
    public  MyBaseAdapter(Context context,String[] str){
        this.context = context;
        this.str = str;
        listOrStr = "str";
    }
    @Override
    public int getCount() {
        if (listOrStr.equals("list")){
            return list==null?0:list.size();
        }else if (listOrStr.equals("str")){
            return str==null?0:str.length;
        }else {
            return 0;
        }

    }

    @Override
    public Object getItem(int i) {
        if (listOrStr.equals("list")){
            return list.get(i);
        }else if (listOrStr.equals("str")){
            return str[i];
        }else {
            return null;
        }

    }

    @Override
    public long getItemId(int i) {
        return i;
    }




}
