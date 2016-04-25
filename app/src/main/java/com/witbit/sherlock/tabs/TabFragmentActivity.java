package com.witbit.sherlock.tabs;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.witbit.sherlock.oneapp.R;

import java.util.ArrayList;
import java.util.List;

public class TabFragmentActivity extends FragmentActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentViewPagerAdapter adapter;

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_fragment);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        for (int i = 0; i < 10; i++) {

            if (i == 0) {
                titles.add("" + i);
                LeftRightListFragment fragment = new LeftRightListFragment();
                fragments.add(fragment);
            } else {
                titles.add("" + i);
                fragments.add(new TabFragment(i));
            }
        }

        adapter = new FragmentViewPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }


    private class FragmentViewPagerAdapter extends FragmentPagerAdapter {

        public FragmentViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position );
        }
    }
}
