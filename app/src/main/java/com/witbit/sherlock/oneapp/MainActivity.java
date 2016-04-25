package com.witbit.sherlock.oneapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.witbit.sherlock.recyclerview.MyRecyclerViewActivity;
import com.witbit.sherlock.tabs.TabFragmentActivity;

import java.util.ArrayList;


public class MainActivity extends Activity {
    private ArrayList<Intent> data = new ArrayList<>();
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        initData();

        ListView listView = (ListView) findViewById(R.id.listView);
        DataAdapter adapter = new DataAdapter();
        listView.setAdapter(adapter);

    }

    private void initData() {
        data.add(newIntent("change skin", ChangeSkinActivity.class));
        data.add(newIntent("fragment tabs", TabFragmentActivity.class));
        data.add(newIntent("RecyclerView", MyRecyclerViewActivity.class));

    }

    private Intent newIntent(String name, Class clazz) {
        Intent intent = new Intent();
        intent.setClass(this, clazz);
        intent.putExtra("name", name);
        return intent;
    }

    class DataAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Intent getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.item_main, null);
            }

            TextView name = (TextView) convertView.findViewById(R.id.textView);

            final Intent intent = getItem(position);
            String strName = intent.getStringExtra("name");

            name.setText(strName);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(intent);
                }
            });


            return convertView;
        }
    }

}