package com.example.aplikasisemar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class TampilAspirasi extends AppCompatActivity {
    private EditText editTextId;
    private EditText editTextCreated;
    private EditText editTextNama;
    private EditText editTextKategori;
    private EditText editTextDeskripsi;

    private TextView back;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_aspirasi);
        Intent intent = getIntent();

        id = intent.getStringExtra(Konfigurasi.EMP_ID);

        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextCreated = (EditText) findViewById(R.id.editTextCreated);
        editTextNama = (EditText) findViewById(R.id.editTextNama);
        editTextKategori = (EditText) findViewById(R.id.editTextKategori);
//        editTextJenis = (EditText) findViewById(R.id.editTextJenis);
        editTextDeskripsi = (EditText) findViewById(R.id.editTextDeskripsi);

        back = (TextView) findViewById(R.id.back);

//        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
//        buttonDelete = (ImageView) findViewById(R.id.buttonDelete);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TampilAspirasi.this, Tanggapan.class));
            }
        });

//        buttonUpdate.setOnClickListener(this);
//        buttonDelete.setOnClickListener(this);

        editTextId.setText(id);

        getEmployee();
    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilAspirasi.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Konfigurasi.URL_GET_AspirasiEMP,id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String created = c.getString(Konfigurasi.TAG_CREATED);
            String nama = c.getString(Konfigurasi.TAG_NAMA);
            String kategori = c.getString(Konfigurasi.TAG_KATEGORI);
//
            String deskripsi = c.getString(Konfigurasi.TAG_DESKRIPSI);

            editTextCreated.setText(created);
            editTextNama.setText(nama);
            editTextKategori.setText(kategori);
//
            editTextDeskripsi.setText(deskripsi);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateEmployee(){
        final String created = editTextCreated.getText().toString().trim();
        final String nama = editTextNama.getText().toString().trim();
        final String kategori = editTextKategori.getText().toString().trim();
//        final String jenis = editTextJenis.getText().toString().trim();
        final String deskripsi = editTextDeskripsi.getText().toString().trim();


        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilAspirasi.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilAspirasi.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Konfigurasi.KEY_EMP_ID,id);
                hashMap.put(Konfigurasi.KEY_EMP_CREATED,created);
                hashMap.put(Konfigurasi.KEY_EMP_NAMA,nama);
                hashMap.put(Konfigurasi.KEY_EMP_KATEGORI,kategori);
//                hashMap.put(Konfigurasi.KEY_EMP_JENIS,jenis);
                hashMap.put(Konfigurasi.KEY_EMP_DESKRIPSI,deskripsi);


                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Konfigurasi.URL_GET_AspirasiEMP,hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    private void deleteEmployee(){
        class DeleteEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilAspirasi.this, "Updating...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilAspirasi.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Konfigurasi.URL_DELETE_EMP, id);
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    private void confirmDeleteEmployee(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Kamu Yakin Ingin Menghapus Data ini ?");

        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteEmployee();
                        startActivity(new Intent(TampilAspirasi.this,TampilSemuaData.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

//    @Override
//    public void onClick(View v) {
//        if(v == buttonUpdate){
//            updateEmployee();
//            startActivity(new Intent(tampilData.this,TampilSemuaData.class));
//        }
//
//        if(v == buttonDelete){
//            confirmDeleteEmployee();
//        }
}
