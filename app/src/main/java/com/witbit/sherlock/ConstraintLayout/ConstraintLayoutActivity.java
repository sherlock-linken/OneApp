package com.witbit.sherlock.ConstraintLayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.witbit.sherlock.oneapp.R;


public class ConstraintLayoutActivity extends AppCompatActivity {

	private ImageView icon;
	private Button button4;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_constraint_layout);

//		button4 = (Button) findViewById(R.id.button4);
		icon = (ImageView) findViewById(R.id.icon);

//		button4.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				if(icon.getVisibility() == View.VISIBLE)
//					icon.setVisibility(View.GONE);
//				else
//					icon.setVisibility(View.VISIBLE);
//			}
//		});

	}
}
