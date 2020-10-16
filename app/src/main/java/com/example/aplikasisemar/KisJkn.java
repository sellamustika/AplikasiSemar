package com.example.aplikasisemar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class KisJkn extends AppCompatActivity {
    private ImageView imgbarang;
    private TextView txtjudul, txtisi;

    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    ArrayList<HashMap<String, String>> list_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kis_jkn);

        getSupportActionBar().setTitle("KIS - JKN");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String url = "http://192.168.43.126/KKI2Coba/getdata.php"; //sesuaikan dengan ip pc anda
        imgbarang = (ImageView)findViewById(R.id.imgbarang);
        txtjudul = (TextView)findViewById(R.id.txtjudul);
        txtisi = (TextView)findViewById(R.id.txtisi);
//        txtstock = (TextView)findViewById(R.id.txtstock);

        requestQueue = Volley.newRequestQueue(KisJkn.this);

        list_data = new ArrayList<HashMap<String, String>>();

        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("tbl_berita");
                    for (int a = 0; a < jsonArray.length(); a ++){
                        JSONObject json = jsonArray.getJSONObject(a);
                        HashMap<String, String> map  = new HashMap<String, String>();
                        map.put("berita_id", json.getString("berita_id"));
                        map.put("berita_judul", json.getString("berita_judul"));
                        map.put("berita_image", json.getString("berita_image"));
                        map.put("berita_isi", json.getString("berita_isi"));
//                        map.put("stock", json.getString("stock"));
                        list_data.add(map);
                    }
                    Glide.with(getApplicationContext())
                            .load("http://192.168.43.126/KKI2Coba/img" + list_data.get(0).get("berita_image"))
                            .crossFade()
                            .placeholder(R.drawable.tangan)
                            .into(imgbarang);
                    txtjudul.setText(list_data.get(0).get("berita_judul"));
                    txtisi.setText(list_data.get(0).get("berita_isi"));
//                    txtstock.setText(list_data.get(0).get("stock"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override            public void onErrorResponse(VolleyError error) {
                Toast.makeText(KisJkn.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);
    }


}
