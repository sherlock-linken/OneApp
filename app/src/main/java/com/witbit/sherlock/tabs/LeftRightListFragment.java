package com.witbit.sherlock.tabs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.witbit.sherlock.oneapp.R;

import java.util.ArrayList;
import java.util.List;

public class LeftRightListFragment extends Fragment {

    private View fragmentView;

    private ListView leftListView;
    private ListView rightListView;

    private List<String> tabTitles = new ArrayList<>();
    private List<List<String>> datas = new ArrayList<>();

    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;


    public LeftRightListFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_left_right_list, container, false);

        leftListView = (ListView) fragmentView.findViewById(R.id.leftListView);
        rightListView = (ListView) fragmentView.findViewById(R.id.rightListView);

        for (int i = 0; i < 10; i++) {
            tabTitles.add("" + i);
            List<String> lineData = new ArrayList<>();
            for (int j = 0; j < 20; j++) {
                lineData.add(i + "" + j);
            }
            datas.add(lineData);
        }


        leftAdapter = new LeftAdapter();
        leftListView.setAdapter(leftAdapter);

        rightAdapter = new RightAdapter(datas.get(0));
        rightListView.setAdapter(rightAdapter);

        leftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                rightAdapter.setDataList(datas.get(position));
                rightAdapter.notifyDataSetChanged();
            }
        });


        return fragmentView;
    }

    private class LeftAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return tabTitles.size();
        }

        @Override
        public String getItem(int position) {
            return tabTitles.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(LeftRightListFragment.this.getContext()).inflate(R.layout.left_list_item, null);
            }

            ((TextView) convertView.findViewById(R.id.textView)).setText(tabTitles.get(position));

            return convertView;
        }
    }


    private class RightAdapter extends BaseAdapter {
        private List<String> dataList;

        public RightAdapter(List<String> dataList) {
            this.dataList = dataList;
        }

        public void setDataList(List<String> dataList) {
            this.dataList = dataList;
        }

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public String getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(LeftRightListFragment.this.getContext()).inflate(R.layout.left_list_item, null);
            }

            ((TextView) convertView.findViewById(R.id.textView)).setText(dataList.get(position));


            return convertView;
        }
    }

}
