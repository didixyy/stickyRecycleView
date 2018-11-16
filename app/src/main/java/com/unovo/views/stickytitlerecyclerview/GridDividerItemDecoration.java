package com.unovo.views.stickytitlerecyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by User on 2017/7/25.
 */

public class GridDividerItemDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    private List<DetailBean> mList;

    public GridDividerItemDecoration(Context context, List<DetailBean> list) {
        super();
        mContext = context;
        mList = list;

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            int position = parent.getChildAdapterPosition(view);
            DetailBean bean = mList.get(position);
            if (bean != null && bean.getType()!=0) {

                outRect.set(0, 0, 0, 0);
            }else{
                int  vertical = mContext.getResources().getDimensionPixelSize(R.dimen.y22);
                int  horizontal= mContext.getResources().getDimensionPixelSize(R.dimen.x16);
                outRect.set(horizontal, vertical, horizontal, vertical);
            }

        } else if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            outRect.set(0, 0, 0, 5);
        } else {
            super.getItemOffsets(outRect, view, parent, state);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}
