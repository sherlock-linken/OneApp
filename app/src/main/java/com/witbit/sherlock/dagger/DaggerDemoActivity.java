package com.witbit.sherlock.dagger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.witbit.sherlock.oneapp.R;

import javax.inject.Inject;

public class DaggerDemoActivity extends AppCompatActivity {

//	@Inject UserModle sherlock;
//	@Inject UserModle girlFriend;
//	@Inject CarModel myCar;

	private UserComponent mUserComponent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dagger_demo);

		mUserComponent = DaggerUserComponent.builder().build();
		mUserComponent.hehehaxi(this);

		findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				Toast.makeText(DaggerDemoActivity.this, girlFriend.userName, Toast.LENGTH_SHORT).show();

//				Log.i("sherlock", "DaggerDemoActivity.onClick car seats == " + myCar.seats);

				IPhoneModle phone = mUserComponent.getPhone();

				Log.i("sherlock", "DaggerDemoActivity.onClick phone == "+phone.getPhoneName());
			}
		});
	}
}
