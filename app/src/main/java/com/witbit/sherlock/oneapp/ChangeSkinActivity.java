package com.witbit.sherlock.oneapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zhy.changeskin.SkinManager;
import com.zhy.changeskin.callback.ISkinChangingCallback;

public class ChangeSkinActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_skin);

        SkinManager.getInstance().register(this);


        findViewById(R.id.button10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.getInstance().changeSkin("");
            }
        });
        findViewById(R.id.button11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.getInstance().changeSkin("red");
            }
        });
        findViewById(R.id.button12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.getInstance().changeSkin("green");
            }
        });
        findViewById(R.id.button13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.getInstance().changeSkin("blue");
            }
        });

        findViewById(R.id.button20).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.getInstance().changeSkin("");
            }
        });
        findViewById(R.id.button21).setOnClickListener(pkgChangeSkinListener);
//        findViewById(R.id.button22).setOnClickListener(pkgChangeSkinListener);
//        findViewById(R.id.button23).setOnClickListener(pkgChangeSkinListener);


//        String path = "file:///android_asset/skin_tanzy";
//        String packageName = ;
    }

    private View.OnClickListener pkgChangeSkinListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SkinManager.getInstance().changeSkin(
                    "file:///android_asset/skin_tanzy",
                    "com.sherlock.oneappskin",
                    new ISkinChangingCallback() {
                        @Override
                        public void onStart() {
                        }

                        @Override
                        public void onError(Exception e) {
                            Toast.makeText(ChangeSkinActivity.this, "换肤失败", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onComplete() {
                            Toast.makeText(ChangeSkinActivity.this, "换肤成功", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().unregister(this);
    }
}
