package com.unovo.views.stickytitlerecyclerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 2017/7/21.
 */

public class DetailRecyclerViewAdapter extends BaseRecyclerAdapter<DetailBean> {
    private OnItemClickListener mOnItemClickListener;

    @Override
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public DetailRecyclerViewAdapter(Context context, List<DetailBean> list) {
        super(context, list);
    }

    @Override
    protected RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType, OnItemClickListener mListener) {
        if (viewType == RoomStatusShowConstant.TYPE_BUILD) {
            return new BuildViewholder(LayoutInflater.from(mContext).inflate(R.layout.view_bulid_title, parent, false));
        }
        if (viewType == RoomStatusShowConstant.TYPE_VILLAGER) {
            return new VillagerViewholder(LayoutInflater.from(mContext).inflate(R.layout.view_villager_title, parent, false));
        }
        if (viewType == RoomStatusShowConstant.TYPE_GLIDE) {
            return new GlideViewholder(LayoutInflater.from(mContext).inflate(R.layout.view_room_status_item, parent, false));
        }
        return new GlideViewholder(LayoutInflater.from(mContext).inflate(R.layout.view_room_status_item, parent, false));
    }

    @Override
    protected void ObindViewHolder(RecyclerView.ViewHolder holder, DetailBean data, final int position) {
        if (holder instanceof BuildViewholder) {
            ((BuildViewholder) holder).mTextview.setText(data.getBuild());
            holder.itemView.setOnClickListener(null);
        } else if (holder instanceof VillagerViewholder) {
            ((VillagerViewholder) holder).mTextview.setText(data.getVillager());
            holder.itemView.setOnClickListener(null);
        } else if (holder instanceof GlideViewholder) {
            ((GlideViewholder) holder).mTvBuildName.setText(data.getBuild());
            ((GlideViewholder) holder).mTvRoomName.setText(data.getName());
            ((GlideViewholder) holder).mTvCheckInTime.setText("2018-09-11");
            ((GlideViewholder) holder).mTvMoney.setText("￥1000");
            ((GlideViewholder) holder).mTvName.setText("张三");
            ((GlideViewholder) holder).mLineRoomStatus.setLineStyle(FatherSunRoomLine.LineStyle.NORMAL);
            ((GlideViewholder) holder).mIvRoomStatus.setBackground(drawText(((GlideViewholder) holder).mIvRoomStatus,"欠",mContext.getResources().getColor(R.color.assist_color_red_ee776f)));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v, position);
                }
            });

        }

    }
    private Drawable drawText(ImageView view, String text,@ColorInt int color) {
        // 创建一个和原图同样大小的位图
        int x=mContext.getResources().getDimensionPixelSize(R.dimen.x56);
        int y=mContext.getResources().getDimensionPixelSize(R.dimen.y56);
        Bitmap newbit = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setColor(color);
        Canvas canvas = new Canvas(newbit);
        canvas.drawRect(0,0,x,y,paint);
        paint.setColor(Color.WHITE);

        paint.setTextSize(mContext.getResources().getDimensionPixelSize(R.dimen.x32));
        // 在原图指定位置写上字
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        int baseline = (x - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        canvas.drawText(text, (x / 2 - bounds.width() / 2) - mContext.getResources().getDimensionPixelSize(R.dimen.x3), baseline, paint);
        canvas.save(Canvas.ALL_SAVE_FLAG);

        // 存储
        canvas.restore();

        return new  BitmapDrawable(newbit);
    }
    @Override
    public int getItemViewType(int position) {
        if (position >= 0 && position < mList.size()) {
            return mList.get(position).getType();
        }
        return RoomStatusShowConstant.TYPE_GLIDE;
    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    public class VillagerViewholder extends RecyclerView.ViewHolder {

        private final TextView mTextview;

        public VillagerViewholder(View itemView) {
            super(itemView);
            mTextview = (TextView) itemView.findViewById(R.id.title_textview);
        }
    }

    public class BuildViewholder extends RecyclerView.ViewHolder {
        private final TextView mTextview;

        public BuildViewholder(View itemView) {
            super(itemView);
            mTextview = (TextView) itemView.findViewById(R.id.title_textview);
        }
    }

    public class GlideViewholder extends RecyclerView.ViewHolder {
        private final TextView mTvMoney;
        private final TextView mTvName;
        private final TextView mTvCheckInTime;
        private final TextView mTvRoomName;
        private final TextView mTvBuildName;
        private final RoomStatusDayTextView mTvRoomStatusDay;
        private final ImageView mIvRoomStatus;
        private final FatherSunRoomLine mLineRoomStatus;

        public GlideViewholder(View itemView) {
            super(itemView);
            mTvMoney = (TextView) itemView.findViewById(R.id.tv_money);
            mTvName = (TextView) itemView.findViewById(R.id.tv_name);
            mTvCheckInTime = (TextView) itemView.findViewById(R.id.tv_check_in_time);
            mTvRoomName = (TextView) itemView.findViewById(R.id.tv_room_name);
            mTvBuildName = (TextView) itemView.findViewById(R.id.tv_build_name);
            mTvRoomStatusDay = (RoomStatusDayTextView) itemView.findViewById(R.id.tv_room_status_day);
            mIvRoomStatus = (ImageView) itemView.findViewById(R.id.iv_room_status);
            mLineRoomStatus = (FatherSunRoomLine) itemView.findViewById(R.id.line_room_status);
        }
    }
}
