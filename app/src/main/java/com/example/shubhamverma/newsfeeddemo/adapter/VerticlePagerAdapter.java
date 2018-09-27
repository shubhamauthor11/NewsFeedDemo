package com.example.shubhamverma.newsfeeddemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.shubhamverma.newsfeeddemo.R;
import com.example.shubhamverma.newsfeeddemo.model.NewsFeedModel;
import com.example.shubhamverma.newsfeeddemo.view.SplashActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class VerticlePagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater mLayoutInflater;

    private List<NewsFeedModel> itemsList;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tv_title)
    TextView tvTitile;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_publishedAt)
    TextView tvPublished;
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    @BindView(R.id.tv_descriptionlink)
    TextView tvDescriptionLink;
    @BindView(R.id.tv_source)
    TextView tvSource;

    public VerticlePagerAdapter(Context context, List<NewsFeedModel> items) {
        this.context = context;
        this.itemsList = items;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.content_main, container, false);
        ButterKnife.bind(this, itemView);
        try {
            final NewsFeedModel beanObj = itemsList.get(position);

            String result = beanObj.getRESPONSE_JSON();
            tvContent.setText(beanObj.getCONTENT_URL());
            tvTitile.setText(beanObj.getTITLE());
            tvPublished.setText("Published At : " + beanObj.getPUBLISHED_TIME());
            tvAuthor.setText("By : " + beanObj.getAUTHOR());
            final String link = beanObj.getDESCRIPTION();
            tvDescriptionLink.setText("Read Full Story");
//            tvDescriptionLink.setText(link);
            tvSource.setText("Source : " + beanObj.getSOURCE());
            String imageURL = beanObj.getIMAGE_URL();


//            tvDescriptionLink.setText("Click my web site: www.stackoverflow.com");

            tvDescriptionLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Uri adress = Uri.parse(beanObj.getDESCRIPTION());
//                        Intent browser = new Intent(Intent.ACTION_VIEW, adress);
//                        browser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        context.startActivity(browser);

                        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                        CustomTabsIntent customTabsIntent = builder.build();
                        customTabsIntent.intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        customTabsIntent.setFlags();
                        customTabsIntent.launchUrl(context, adress);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });


            final ProgressBar progressBar = new ProgressBar(context);

            // Show progress bar
            progressBar.setVisibility(View.VISIBLE);
// Hide progress bar on successful load
            Picasso.get().load(imageURL)
                    .error(R.drawable.ic_launcher_background)
                    .fit()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imageView, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            if (progressBar != null) {
                                progressBar.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onError(Exception e) {
                            if (progressBar != null) {
                                progressBar.setVisibility(View.GONE);
                            }
                        }

                    });


            container.addView(itemView);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

}
