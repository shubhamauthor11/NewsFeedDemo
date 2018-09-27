package com.example.shubhamverma.newsfeeddemo.view;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.example.shubhamverma.newsfeeddemo.R;
import com.example.shubhamverma.newsfeeddemo.common.ApiClient;
import com.example.shubhamverma.newsfeeddemo.common.ApiInterface;
import com.example.shubhamverma.newsfeeddemo.common.Constants;
import com.example.shubhamverma.newsfeeddemo.common.WebserviceUrl;
import com.example.shubhamverma.newsfeeddemo.database.AppDatabase;
import com.example.shubhamverma.newsfeeddemo.model.NewsFeedModel;
import com.example.shubhamverma.newsfeeddemo.requestModel.NewsResponseModel;

import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity implements Callback<NewsResponseModel> {

    //    @BindView(R.id.progress_bar)
//    ProgressBar progressBar;
    ProgressDialog progressDialog;
    private ApiInterface apiService;
    private Context context;
    private TopNewsFeedFragment topNewsFeedFragment;
    private AppDatabase myDb;
    String date = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        context = SplashActivity.this;
        myDb = AppDatabase.getInstance(context);
        progressDialog = new ProgressDialog(context);
        date = Constants.currentDate();
        getData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void getData() {
        progressDialog.show();
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Top News");
        progressDialog.setIndeterminate(true);
        List<NewsFeedModel> dataList = myDb.newsFeedDao().getDataWithDate(date);
        if (dataList.size() <= 0) {
            Call<NewsResponseModel> call = apiService.getNewsResponse(WebserviceUrl.apiKey);
            call.enqueue(SplashActivity.this);

        } else {
            progressDialog.hide();
            topNewsFeedFragment = new TopNewsFeedFragment();
            switchFragment(topNewsFeedFragment, "TopNewsFeedFragment");
        }


    }

    @Override
    public void onResponse(Call<NewsResponseModel> call, Response<NewsResponseModel> response) {

        String resposeString = "";
        if (response != null) {
            try {

                String status = response.body().getStatus();

                NewsResponseModel newsResponse = response.body();

                if (status.equalsIgnoreCase("ok")) {

                    String totalResults = newsResponse.getTotalResults();

                    List<NewsResponseModel.Article> articleArray = newsResponse.getArticles();

                    int articleArrayLength = articleArray.size();
                    ContentValues cv = new ContentValues();

                    if (articleArrayLength > 0) {

                        for (int i = 0; i < articleArrayLength; i++) {

                            NewsResponseModel.Article innerElem = articleArray.get(i);

                            if (innerElem != null) {

                                String title = innerElem.getTitle();
                                String author = innerElem.getAuthor();
                                String description = innerElem.getUrl();
                                String content = innerElem.getContent();
                                String imageUrl = innerElem.getUrlToImage();
                                String publishedTime = innerElem.getPublishedAt();
                                String source = innerElem.getSource().getName();


                                NewsFeedModel newsFeed = new NewsFeedModel();
                                newsFeed.setTITLE(title);
                                newsFeed.setAUTHOR(author);
                                newsFeed.setDESCRIPTION(description);
                                newsFeed.setCONTENT_URL(content);
                                newsFeed.setIMAGE_URL(imageUrl);
                                newsFeed.setPUBLISHED_TIME(publishedTime);
                                newsFeed.setSOURCE(source);
                                newsFeed.setDATE_TIME(date);

                                myDb.newsFeedDao().insert(newsFeed);

                            }

                        }
                        topNewsFeedFragment = new TopNewsFeedFragment();
                        switchFragment(topNewsFeedFragment, "TopNewsFeedFragment");
                    }

                    progressDialog.hide();


                } else {
                    progressDialog.hide();
                }
            } catch (Exception e) {
                e.printStackTrace();
                progressDialog.hide();
            }
        }
    }

    @Override
    public void onFailure(Call<NewsResponseModel> call, Throwable t) {
        progressDialog.hide();
    }

    private void switchFragment(Fragment frag, String name) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_layout, frag, name);
        ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(name);
        ft.commitAllowingStateLoss();

    }

    private static NewsFeedModel addUser(final AppDatabase db, NewsFeedModel newsFeed) {
        db.newsFeedDao().insertAll(newsFeed);
        return newsFeed;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
// TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);


    }
}
