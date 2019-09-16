package com.example.achmadqomarudin.beritakucom;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by Achmad Qomarudin on 07/12/2017.
 */

public class AdapterRecyclerView extends RecyclerView.Adapter{

    private Context context;
    public List<ModelBerita> data;
    private ViewHolder viewHolder;

    AdapterRecyclerView(List<ModelBerita> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false);
        return viewHolder = new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        viewHolder.tvTitle.setText(data.get(position).getTitle());
        viewHolder.tvDate.setText(data.get(position).getPubDate());
        viewHolder.tvDeskripsi.setText(data.get(position).getLink());

        Glide.with(context).load(data.get(position).getLink_image())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.gif)
                .into(viewHolder.tvImg);

        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("img", data.get(position).getLink_image());
                i.putExtra("title", data.get(position).getTitle());
                i.putExtra("deskripsi", data.get(position).getDescription());
                i.putExtra("link", data.get(position).getLink());
                i.putExtra("date", data.get(position).getPubDate());
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
//        if (data.size() == 0) {
//            Toast.makeText(context, "Data Kosong", Toast.LENGTH_SHORT).show();
//        }
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
