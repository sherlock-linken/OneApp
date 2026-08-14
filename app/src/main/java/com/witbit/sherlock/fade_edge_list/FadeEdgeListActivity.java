package com.witbit.sherlock.fade_edge_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.witbit.sherlock.oneapp.R;

public class FadeEdgeListActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fade_edge_list);

        String[] titles = getResources().getStringArray(R.array.fade_edge_card_titles);
        String[] descriptions = getResources().getStringArray(R.array.fade_edge_card_descriptions);

        FadeEdgeRecyclerView controlledList = (FadeEdgeRecyclerView) findViewById(R.id.controlledFadeEdgeList);
        controlledList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        controlledList.setFadingEdgeLength(dp(72));
        controlledList.setAdapter(new DemoAdapter(titles, descriptions));

        RecyclerView systemList = (RecyclerView) findViewById(R.id.systemFadeEdgeList);
        systemList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        systemList.setHorizontalFadingEdgeEnabled(true);
        systemList.setFadingEdgeLength(dp(72));
        systemList.setOverScrollMode(View.OVER_SCROLL_NEVER);
        systemList.setAdapter(new DemoAdapter(titles, descriptions));
    }

    private int dp(int value) {
        return (int) (value * getResources().getDisplayMetrics().density + 0.5f);
    }

    private static class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.ViewHolder> {
        private final String[] titles;
        private final String[] descriptions;

        DemoAdapter(String[] titles, String[] descriptions) {
            this.titles = titles;
            this.descriptions = descriptions;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_fade_edge_card, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.title.setText(titles[position]);
            holder.description.setText(descriptions[position]);
        }

        @Override
        public int getItemCount() {
            return Math.min(titles.length, descriptions.length);
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            final TextView title;
            final TextView description;

            ViewHolder(@NonNull View itemView) {
                super(itemView);
                title = (TextView) itemView.findViewById(R.id.tvTitle);
                description = (TextView) itemView.findViewById(R.id.tvDescription);
            }
        }
    }
}
