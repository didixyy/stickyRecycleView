package com.unovo.views.stickytitlerecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by User on 2017/7/21.
 */

public abstract  class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected List<T> mList = new ArrayList<>();
    protected OnItemClickListener mListener;
    protected Context mContext;
    protected LayoutInflater mInflater;
    protected abstract RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType,OnItemClickListener mListener);
    protected abstract void ObindViewHolder(RecyclerView.ViewHolder holder,T data, int viewType);
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    public BaseRecyclerAdapter(Context context,List<T> list){
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        if(list!=null)
        this.mList = list;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createViewHolder(parent,viewType,mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ObindViewHolder(holder,mList.get(position),position);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

}
