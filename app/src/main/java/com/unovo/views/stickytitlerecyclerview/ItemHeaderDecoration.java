package com.unovo.views.stickytitlerecyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by User on 2017/7/24.
 */

public class ItemHeaderDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    private List<DetailBean> mList;
    public static String currentVillagerTag = "0";
    public static String currentBuildTag = "0";
    private LayoutInflater mLayoutInflater;
    private int mTitleHight = 50;
    private int mTitleTextSize = 20;
    public ItemHeaderDecoration(Context context , List<DetailBean> dataList) {
        super();
        this.mContext = context;
        this.mList = dataList;
        mTitleHight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50,context.getResources().getDisplayMetrics());
       // mTitleTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, context.getResources().getDisplayMetrics());
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {

        //小区吸顶部分
        //获取到视图中第一个可见的item的position
        final int position = ((GridLayoutManager)parent.getLayoutManager()).findFirstVisibleItemPosition();
        String villager = mList.get(position).getVillager();

        View child = parent.findViewHolderForLayoutPosition(position).itemView;

        if((position+1) < mList.size()){
            String suspensionTag = mList.get(position+1).getVillager();
            if(null!=villager && !villager.equals(suspensionTag)){
                Log.d("ZHG-TEST","!!!!!!!!!!!!!child.Height() = "+child.getHeight()+" , child.top = "+child.getTop()+" , mTitleHight = "+mTitleHight);
                if(child.getHeight()+child.getTop() < mTitleHight){
                    c.save();
                    c.translate(0,child.getHeight()+child.getTop()-mTitleHight);
                }
            }
        }


        View topTitleView = mLayoutInflater.inflate(R.layout.view_villager_title,parent,false);
        TextView textView = (TextView) topTitleView.findViewById(R.id.title_textview);
        textView.setText(villager);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"点击了"+position,Toast.LENGTH_LONG).show();
            }
        });


        int toDrawWidthSpec;//用于测量的widthMeasureSpec
        int toDrawHeightSpec;//用于测量的heightMeasureSpec
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) topTitleView.getLayoutParams();
        if (lp == null) {
            lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);//这里是根据复杂布局layout的width height，new一个Lp
            topTitleView.setLayoutParams(lp);
        }
        topTitleView.setLayoutParams(lp);
        if (lp.width == ViewGroup.LayoutParams.MATCH_PARENT) {
            //如果是MATCH_PARENT，则用父控件能分配的最大宽度和EXACTLY构建MeasureSpec。
            toDrawWidthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth() - parent.getPaddingLeft() - parent.getPaddingRight(), View.MeasureSpec.EXACTLY);
        } else if (lp.width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            //如果是WRAP_CONTENT，则用父控件能分配的最大宽度和AT_MOST构建MeasureSpec。
            toDrawWidthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth() - parent.getPaddingLeft() - parent.getPaddingRight(), View.MeasureSpec.AT_MOST);
        } else {
            //否则则是具体的宽度数值，则用这个宽度和EXACTLY构建MeasureSpec。
            toDrawWidthSpec = View.MeasureSpec.makeMeasureSpec(lp.width, View.MeasureSpec.EXACTLY);
        }
        //高度同理
        if (lp.height == ViewGroup.LayoutParams.MATCH_PARENT) {
            toDrawHeightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight() - parent.getPaddingTop() - parent.getPaddingBottom(), View.MeasureSpec.EXACTLY);
        } else if (lp.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            toDrawHeightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight() - parent.getPaddingTop() - parent.getPaddingBottom(), View.MeasureSpec.AT_MOST);
        } else {
            toDrawHeightSpec = View.MeasureSpec.makeMeasureSpec(mTitleHight, View.MeasureSpec.EXACTLY);
        }
        //依次调用 measure,layout,draw方法，将复杂头部显示在屏幕上。
        topTitleView.measure(toDrawWidthSpec, toDrawHeightSpec);
        topTitleView.layout(parent.getPaddingLeft(), parent.getPaddingTop(), parent.getPaddingLeft() + topTitleView.getMeasuredWidth(), parent.getPaddingTop() + topTitleView.getMeasuredHeight());
        topTitleView.draw(c);//Canvas默认在视图顶部，无需平移，直接绘制

        //楼从吸顶部分
        String build = mList.get(position).getBuild();
        if((position+2) < mList.size()){
            String suspensionTag = mList.get(position+2).getBuild();
            if(null!=build && !build.equals(suspensionTag)){
                Log.d("ZHG-TEST","!!!!!!!!!!!!!child.Height() = "+child.getHeight()+" , child.top = "+child.getTop()+" , mTitleHight = "+mTitleHight);
                if(child.getHeight()+child.getTop() < mTitleHight){
                    c.save();
                    c.translate(0,child.getHeight()+child.getTop()-mTitleHight);
                }
            }
        }



        View buildTitleView = mLayoutInflater.inflate(R.layout.view_bulid_title,parent,false);
        TextView bulidTextView = (TextView) buildTitleView.findViewById(R.id.title_textview);
        bulidTextView.setText(build);
        bulidTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"点击了"+position,Toast.LENGTH_LONG).show();
            }
        });

        int tobulidDrawWidthSpec;//用于测量的widthMeasureSpec
        int  tobulidHeightSpec;//用于测量的heightMeasureSpec
        RecyclerView.LayoutParams bulidlp = (RecyclerView.LayoutParams) buildTitleView.getLayoutParams();
        if (bulidlp == null) {
            bulidlp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);//这里是根据复杂布局layout的width height，new一个Lp
            buildTitleView.setLayoutParams(lp);
        }
        buildTitleView.setLayoutParams(lp);
        if (bulidlp.width == ViewGroup.LayoutParams.MATCH_PARENT) {
            //如果是MATCH_PARENT，则用父控件能分配的最大宽度和EXACTLY构建MeasureSpec。
            tobulidDrawWidthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth() - parent.getPaddingLeft() - parent.getPaddingRight(), View.MeasureSpec.EXACTLY);
        } else if (lp.width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            //如果是WRAP_CONTENT，则用父控件能分配的最大宽度和AT_MOST构建MeasureSpec。
            tobulidDrawWidthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth() - parent.getPaddingLeft() - parent.getPaddingRight(), View.MeasureSpec.AT_MOST);
        } else {
            //否则则是具体的宽度数值，则用这个宽度和EXACTLY构建MeasureSpec。
            tobulidDrawWidthSpec = View.MeasureSpec.makeMeasureSpec(lp.width, View.MeasureSpec.EXACTLY);
        }
        //高度同理
        if (bulidlp.height == ViewGroup.LayoutParams.MATCH_PARENT) {
            tobulidHeightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight() - parent.getPaddingTop() - parent.getPaddingBottom(), View.MeasureSpec.EXACTLY);
        } else if (lp.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            tobulidHeightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight() - parent.getPaddingTop() - parent.getPaddingBottom(), View.MeasureSpec.AT_MOST);
        } else {
            tobulidHeightSpec = View.MeasureSpec.makeMeasureSpec(mTitleHight, View.MeasureSpec.EXACTLY);
        }
        //依次调用 measure,layout,draw方法，将复杂头部显示在屏幕上。
        buildTitleView.measure(tobulidDrawWidthSpec, tobulidHeightSpec);
        buildTitleView.layout(parent.getPaddingLeft(), parent.getPaddingTop(), parent.getPaddingLeft() + buildTitleView.getMeasuredWidth(), parent.getPaddingTop() + buildTitleView.getMeasuredHeight());
//        if(TextUtils.isEmpty(build)){
//            bulidTextView.setVisibility(View.INVISIBLE);
//        }else {
//            bulidTextView.setVisibility(View.VISIBLE);
//
//        }
        c.translate(0,mTitleHight);
        buildTitleView.draw(c);//Canvas默认在视图顶部，无需平移，直接绘制




    }
}
