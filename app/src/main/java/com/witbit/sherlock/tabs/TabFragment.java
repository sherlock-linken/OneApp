package com.witbit.sherlock.tabs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.witbit.sherlock.oneapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 */
public class TabFragment extends Fragment {

    private int index;

    private View fragmentView;

    public TabFragment() {

    }

    public TabFragment(int mIndex) {
        this.index = mIndex;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_tab, container, false);

        ((TextView) fragmentView.findViewById(R.id.fragment_text)).setText("" + index + "" + index + "" + index + "" + index + "" + index);

        return fragmentView;
    }


}
