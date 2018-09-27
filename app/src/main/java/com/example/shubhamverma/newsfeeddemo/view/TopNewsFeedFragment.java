package com.example.shubhamverma.newsfeeddemo.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shubhamverma.newsfeeddemo.R;
import com.example.shubhamverma.newsfeeddemo.adapter.VerticlePagerAdapter;
import com.example.shubhamverma.newsfeeddemo.database.AppDatabase;
import com.example.shubhamverma.newsfeeddemo.model.NewsFeedModel;
import com.example.shubhamverma.newsfeeddemo.presenter.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopNewsFeedFragment extends Fragment {

    private NewsAdapter adapter;
    private View rootView = null;
    private List<NewsFeedModel> arrListData = new ArrayList<NewsFeedModel>();
    private AppDatabase myDb;
    private Context context;

    @BindView(R.id.vPager)
    VerticalViewPager verticalViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        try {
            rootView = inflater.inflate(R.layout.fragment_top_news, container, false);
            ButterKnife.bind(this, rootView);
            context = TopNewsFeedFragment.this.getActivity();
            myDb = AppDatabase.getInstance(context);
            preparedListData();
            initSwipePager();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rootView;
    }

    private void initSwipePager() {
        verticalViewPager.setAdapter(new VerticlePagerAdapter(getActivity().getApplicationContext(), arrListData));
    }


    private void preparedListData() {

        arrListData = myDb.newsFeedDao().getAll();

    }

}



