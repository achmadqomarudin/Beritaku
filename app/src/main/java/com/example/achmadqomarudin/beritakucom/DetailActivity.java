package com.example.achmadqomarudin.beritakucom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public ImageView ivImg;
    public TextView tvTitle, tvDeskripsi, tvLink, tvDate;
    public List<ModelBerita> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivImg = (ImageView) findViewById(R.id.iv_detail_img);
        tvTitle = (TextView) findViewById(R.id.tv_detail_title);
        tvDeskripsi = (TextView) findViewById(R.id.tv_detail_deskripsi);
        tvLink = (TextView) findViewById(R.id.tv_detail_link);
        tvDate = (TextView) findViewById(R.id.tv_detail_date);

        Intent i = getIntent();

        tvTitle.setText(i.getStringExtra("title"));
        tvDeskripsi.setText(Html.fromHtml(Html.fromHtml(i.getStringExtra("deskripsi")).toString()));
        tvLink.setText(i.getStringExtra("link"));
        tvDate.setText(i.getStringExtra("date"));

        Glide.with(this).load(i.getStringExtra("img"))
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.gif)
                .into(ivImg);
    }

    @Override
    protected void onPause() {
        overridePendingTransition(0, 0);
        super.onPause();
    }
}
