package com.example.achmadqomarudin.beritakucom;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Achmad Qomarudin on 07/12/2017.
 */

public class AsyncTack extends AsyncTask<String, String, String> {

    private String TAG = AsyncTack.class.getSimpleName();
    private RecyclerView recyclerView;
    private Context context;

    AsyncTack(RecyclerView recyclerView, Context context) {
        this.recyclerView = recyclerView;
        this.context = context;
    }
    //function yg bekerja sebelum dijalankan.
    @Override
    protected void onPreExecute() {
        Log.e(TAG, "jalan");
    }
    //proses di belakang layar / untuk get api.
    @Override
    protected String doInBackground(String... strings) {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(ApiUtil.api_detik_hot)
                .get()
                .build();

        try {
            /*Get response API*/
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //proses pengambilan data object, array, atau object array.
    @Override
    protected void onPostExecute(String s) {
//        Log.e("Response API", s);

        if (s == null) {
            Toast.makeText(context, "Internet no connection, please reload again...", Toast.LENGTH_SHORT).show();
        } else {

            try {
                JSONObject jsonOnject = new JSONObject(s);

                JSONArray items = jsonOnject.getJSONArray("items");

                List<ModelBerita> data = new ArrayList<>();

                for (int i = 0; i < items.length(); i++) {
                    JSONObject jsonObject = items.getJSONObject(i);

                    ModelBerita modelData = new ModelBerita();

                    modelData.setTitle(jsonObject.getString("title"));
                    modelData.setDescription(jsonObject.getString("description"));
                    modelData.setLink(jsonObject.getString("link"));
                    modelData.setPubDate(jsonObject.getString("pubDate"));

                    JSONObject enclosure = jsonObject.getJSONObject("enclosure");

                    modelData.setLink_image(enclosure.getString("link"));

                    Log.e("deskripsi", jsonObject.getString("description"));
                    data.add(modelData);
                }

                AdapterRecyclerView view = new AdapterRecyclerView(data, context);
                recyclerView.setAdapter(view);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        super.onPostExecute(s);
    }
}
