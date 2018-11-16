package com.unovo.views.stickytitlerecyclerview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by yuepeng on 2016/11/5.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private final SparseArray<View> views;
    public View rootView;
    private Context mContext;

    public RecyclerViewHolder(Context context, View itemView) {
        super(itemView);
        views = new SparseArray<>();
        rootView = itemView;
        mContext = context;
    }

    /**
     * 返回一个具体的view对象
     */
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = rootView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public void setText(int resId, CharSequence text) {

        TextView view = getView(resId);
        if (text == null) {
            view.setText("");
            return;
        }
        view.setText(text);
    }
    public void setHint(int resId, CharSequence text) {

        TextView view = getView(resId);
        if (text == null) {
            view.setText("");
            return;
        }
        view.setHint(text);
    }
    public CharSequence getText(int resId) {

        TextView view = getView(resId);
       return view.getText();
    }
    public void setSelection(int resId,int selection) {

        EditText view = getView(resId);

        view.setSelection(selection);
    }
    public void setTextColor(int resId, int color) {

        TextView view = getView(resId);

        view.setTextColor(color);
    }
    public View getConvertView() {
        return rootView;
    }



//    public void setImageByUrl(int resId, String url) {
//        ImageView imageView = getView(resId);
//        Glide.with(mContext).load(url).asBitmap().into(imageView);
//    }

    public void setOnClickListener(int resId, View.OnClickListener onClickListener) {
        View view = getView(resId);
        view.setOnClickListener(onClickListener);
    }
    public void setOnCheckedChangeListener(int resId, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        CheckBox view = getView(resId);
        view.setOnCheckedChangeListener(onCheckedChangeListener);
    }
    public void setChecked(int resId, boolean isChecked) {
        CheckBox view = getView(resId);
        view.setChecked(isChecked);
    }
    public void setTextWatcher(int resId, TextWatcher textWatcher) {
        EditText view = getView(resId);
        view.addTextChangedListener(textWatcher);
    }
    public void setOnLongClickListener(int resId, View.OnLongClickListener onLongClickListener) {
        View view = getView(resId);
        view.setOnLongClickListener(onLongClickListener);
    }

    public void setVisibility(int resId, int visibility) {
        View view = getView(resId);
        view.setVisibility(visibility);
    }

    public void setTag(int resId, Object tag) {
        View view = getView(resId);
        view.setTag(tag);
    }

    public void setImageResource(int resId, int ImageResource) {
        ImageView imageView = getView(resId);
        imageView.setImageResource(ImageResource);
    }

    public void setImageDrawable(int resId, Drawable drawable) {
        ImageView imageView = getView(resId);
        imageView.setImageDrawable(drawable);
    }

    public void setImageBitmap(int resId, Bitmap bitmap) {
        ImageView imageView = getView(resId);
        imageView.setImageBitmap(bitmap);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setBackground(int resId, Drawable drawable) {
        View view = getView(resId);
        view.setBackground(drawable);
    }

    public void setBackgroundResource(int resId, int BackgroundResource) {
        View view = getView(resId);
        view.setBackgroundResource(BackgroundResource);
    }

    public void setEnable(int resId, boolean enable) {
        View view = getView(resId);
        view.setEnabled(enable);
    }
    public boolean getChecked(int resId){
        CheckBox view = getView(resId);
        return view.isChecked();
    }
    public <T> T getTag(int resId, Class<T> tClass) {
        View view = getView(resId);
        T t = (T) view.getTag();
        return t;
    }
    //// TODO: 需要在定义其他的支持方法
}
