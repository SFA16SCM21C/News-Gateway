package com.example1.archi.assign6;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsFragment extends Fragment {

    private static final String TAG = "NewsFragment";
    static MainActivity mainActivity;
    private static final String SERILIZE_ARTICAL= "SERILIZE_ARTICAL";
    public static final String NEWS_NUMBER = "NEWS_NUMBER";

    public static final NewsFragment newInstance(Artical artical,String articalNumber,MainActivity inMainActivity){
        NewsFragment fragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SERILIZE_ARTICAL,artical);
        bundle.putString(NEWS_NUMBER,articalNumber);
        fragment.setArguments(bundle);
        mainActivity = inMainActivity;
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.news_frament, container, false);
        try{
            final Artical artical = (Artical) getArguments().getSerializable(SERILIZE_ARTICAL);
            String articaNumber = getArguments().getString(NEWS_NUMBER);

            TextView titleTextView = (TextView) v.findViewById(R.id.titleTV);
            titleTextView.setText(artical.getArticalTitle());
            TextView descriptionTextView = (TextView) v.findViewById(R.id.descriptionTV);
            descriptionTextView.setText(artical.getArticalDescrption());
            TextView titleTextnumber = (TextView) v.findViewById(R.id.articalNumTV);
            titleTextnumber.setText(String.valueOf(articaNumber));

            TextView text_date = (TextView) v.findViewById(R.id.publishedAtTV);

            text_date.setText(artical.getArticalPublishedAt());

            TextView text_author = (TextView) v.findViewById(R.id.authorTV);
            text_author.setText(artical.getArticalAuthor() ==null?"Anonymous":artical.getArticalAuthor());

            ImageView imageView = (ImageView) v.findViewById(R.id.newsImageView);
            Log.d(TAG,"Image view image URL"+artical.getArticalUrlToImag());
            setImage(imageView,artical.getArticalUrlToImag());

            descriptionTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openExternalLink(artical);
                }
            });

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openExternalLink(artical);
                }
            });

            titleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openExternalLink(artical);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG,"Error in onCreateView ---> NewsFragment");
        }
        return v;
    }

    public void setImage(final ImageView imageView1, final String Url){
        if (Url==null) {
            imageView1.setImageResource(R.drawable.blankimage);
        } else {

            Picasso picasso = new Picasso.Builder(mainActivity)
                    .listener(new Picasso.Listener() {
                        @Override
                        public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                            final String changedUrl = Url.replace("http:", "https:");
                            picasso.load(changedUrl)
                                    .fit()
                                    .error(R.drawable.brokenimage)
                                    .placeholder(R.drawable.placeholder)
                                    .into(imageView1);
                        }
                    })
                    .build();

            picasso.load(Url)
                    .error(R.drawable.brokenimage)
                    .placeholder(R.drawable.placeholder)
                    .into(imageView1);
        }
    }

    public void openExternalLink(Artical artical){
        String url = artical.getArticalURL();
        if(url !=null) {
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        }else{

        }
    }
}
