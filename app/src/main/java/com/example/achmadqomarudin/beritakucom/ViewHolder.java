package com.example.achmadqomarudin.beritakucom;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Achmad Qomarudin on 07/12/2017.
 */

class ViewHolder extends RecyclerView.ViewHolder {

    TextView tvTitle, tvDeskripsi, tvDate;
    ImageView tvImg;
    RelativeLayout container;

    ViewHolder(View rootView) {
        super(rootView);

        tvTitle = rootView.findViewById(R.id.tv_title);
        tvImg = rootView.findViewById(R.id.iv_img);
        tvDeskripsi = rootView.findViewById(R.id.tv_deskripsi);
        tvDate = rootView.findViewById(R.id.tv_date);
        container = rootView.findViewById(R.id.container);
    }
}
