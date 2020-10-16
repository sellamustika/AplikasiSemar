package com.example.aplikasisemar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TampilSemuaData extends AppCompatActivity implements ListView.OnItemClickListener{
    private ListView listView;
    private ImageView backlink;
 private Button  btnAddNew,btn_aspirasi;
    private Button  btn_tanggap;
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_semua_data);

        listView = (ListView) findViewById(R.id.listView);
        btnAddNew = (Button) findViewById(R.id.btnAddNew);
        backlink = (ImageView) findViewById(R.id.backlink);
        btn_tanggap = (Button) findViewById(R.id.btn_tanggap);
        btn_aspirasi = (Button) findViewById(R.id.btn_aspirasi);


        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampilAddNew();
            }
        });
        btn_tanggap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tanggapan();
            }
        });
        btn_aspirasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aspirasi();
            }
        });


        backlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampilPengaduan();
            }
        });

        listView.setOnItemClickListener(this);
        getJSON();
    }

    private void tampilPengaduan() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void tanggapan() {
        Intent intent = new Intent(this,Tanggapan.class);
        startActivity(intent);
    }
    private void aspirasi() {
        Intent intent = new Intent(this,Aspirasi.class);
        startActivity(intent);
    }


    private void tampilAddNew() {
        Intent intent = new Intent(this, AddData.class);
        startActivity(intent);
    }


    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Konfigurasi.TAG_ID);
                String nama = jo.getString(Konfigurasi.TAG_NAMA);

                HashMap<String,String> employees = new HashMap<>();
                employees.put(Konfigurasi.TAG_ID,id);
                employees.put(Konfigurasi.TAG_NAMA,nama);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                TampilSemuaData.this, list, R.layout.list_item,
                new String[]{Konfigurasi.TAG_ID,Konfigurasi.TAG_NAMA},
                new int[]{R.id.id, R.id.nama});

        listView.setAdapter(adapter);
    } private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilSemuaData.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Konfigurasi.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, tampilData.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String empId = map.get(Konfigurasi.TAG_ID).toString();
        intent.putExtra(Konfigurasi.EMP_ID,empId);
        startActivity(intent);
    }
}

