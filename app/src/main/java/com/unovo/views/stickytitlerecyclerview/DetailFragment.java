package com.unovo.views.stickytitlerecyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class DetailFragment extends Fragment  implements OnItemClickListener{
    private  RecyclerView mRecyclerView;
    private DetailRecyclerViewAdapter mAdapter;
    private List<DetailBean> mList;
    private GridLayoutManager mLayoutManager;
    public DetailFragment(){
       initData();
    }

    private void initData() {
        mList = new ArrayList<>();
        for(int i = 0 ; i < 15 ; i++){
            DetailBean bean = new DetailBean();
            bean.setType(RoomStatusShowConstant.TYPE_VILLAGER);
            bean.setVillager("小区"+i);
            bean.setName("小区"+i);
            bean.setBuild("楼层"+0);
            mList.add(bean);
            for(int j = 0 ; j < 2 ; j++){
                bean = new DetailBean();
                bean.setType(RoomStatusShowConstant.TYPE_BUILD);
                bean.setVillager("小区"+i);
                bean.setBuild("楼层"+j);
                mList.add(bean);
                for (int x = 0; x < 7; x++) {
                    bean = new DetailBean();
                    bean.setType(RoomStatusShowConstant.TYPE_GLIDE);
                    bean.setVillager("小区"+i);
                    bean.setBuild("楼层"+j);
                    bean.setName("房间"+x);
                    mList.add(bean);
                }



            }

        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = initView(inflater,container);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.detail_recycler);
        mAdapter = new DetailRecyclerViewAdapter(getActivity(),mList);
        mAdapter.setOnItemClickListener(this);
        mLayoutManager = new GridLayoutManager(getActivity(),3);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mList.get(position).getType()!=0?3:1;
            }
        });
        mRecyclerView.addItemDecoration(new GridDividerItemDecoration(getActivity(),mList));
//        mRecyclerView.addItemDecoration(new ItemHeaderDecoration(getActivity(),mList));
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    private View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View view  = inflater.inflate(R.layout.detail_fragment_layout,container,false);
        return view;
    }

    @Override
    public void onItemClick(View itemView, int position) {
        Toast.makeText(getActivity(),"点击了"+position,Toast.LENGTH_LONG).show();
    }
}
