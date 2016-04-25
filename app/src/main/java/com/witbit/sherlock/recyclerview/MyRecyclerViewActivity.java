package com.witbit.sherlock.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.witbit.sherlock.oneapp.R;

import java.util.ArrayList;

public class MyRecyclerViewActivity extends AppCompatActivity {

	private ArrayList<String> dataList = new ArrayList<>();
	private ArrayList<Integer> dataImageList = new ArrayList<>();
	private RecyclerView recyclerView;
	private MyRecyclerAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_recycler_view);

		initData();

		recyclerView = (RecyclerView) findViewById(R.id.recycler);

		adapter = new MyRecyclerAdapter();

		LinearLayoutManager manager = new LinearLayoutManager(this);
		manager.setOrientation(LinearLayoutManager.HORIZONTAL);

		recyclerView.setLayoutManager(manager);
		recyclerView.setAdapter(adapter);


	}

	private void initData() {
		for (int i = 'A'; i <= 'z'; i++) {
			dataList.add("" + (char) i);

		}

		for (int i = 0; i < 24; i++) {
			switch (i % 4) {
				case 0: {
					dataImageList.add(R.drawable.logo);
				}
				break;
				case 1: {
					dataImageList.add(R.drawable.logo1);
				}
				break;
				case 2: {
					dataImageList.add(R.drawable.logo2);
				}
				break;
				case 3: {
					dataImageList.add(R.drawable.logo3);
				}
				break;
			}
		}
	}

	private class RVViewHolder extends RecyclerView.ViewHolder {
		private TextView upText;
		private TextView downText;

		public RVViewHolder(View itemView) {
			super(itemView);
			upText = (TextView) itemView.findViewById(R.id.up_text);
			downText = (TextView) itemView.findViewById(R.id.down_text);
		}
	}

	private class RVIViewHolder extends RecyclerView.ViewHolder {
		private ImageView imageView;


		public RVIViewHolder(View itemView) {
			super(itemView);
			imageView = (ImageView) itemView.findViewById(R.id.image);
		}
	}

	private class MyRecyclerAdapter extends RecyclerView.Adapter<RVIViewHolder> {

		@Override
		public RVIViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			//获取holder
			//			View itemView = LayoutInflater.from(MyRecyclerViewActivity.this).inflate(R.layout
			// .item_recycler, parent,false);
			//			RVViewHolder holder = new RVViewHolder(itemView);


			View itemView = LayoutInflater.from(MyRecyclerViewActivity.this).inflate(R.layout.item_recycler_img,
					parent, false);
			RVIViewHolder holder = new RVIViewHolder(itemView);

			return holder;
		}

		@Override
		public void onBindViewHolder(RVIViewHolder holder, int position) {
			//对holder里面的内容赋值
			int data = dataImageList.get(position);
			//			holder.upText.setText(data);
			//			holder.downText.setText(data + "" + data);

			holder.imageView.setImageResource(data);

		}

		@Override
		public int getItemCount() {
			return dataImageList.size();
		}
	}
}
