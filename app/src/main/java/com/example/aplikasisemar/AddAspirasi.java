package com.example.aplikasisemar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class AddAspirasi extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextNama;

    private EditText editTextAspirasi;
    private TextView back;

    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aspirasi);
        //Inisialisasi dari View
        editTextNama = (EditText) findViewById(R.id.editTextNama);

//        editTextJenis = (EditText) findViewById(R.id.editTextJenis);
        editTextAspirasi = (EditText) findViewById(R.id.editTextAspirasi);
        back  = (TextView) findViewById(R.id.back);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
//        buttonView = (Button) findViewById(R.id.buttonView);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
//        buttonView.setOnClickListener(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddAspirasi.this, TampilSemuaData.class));
            }
        });
    }


    //Dibawah ini merupakan perintah untuk Menambahkan Pegawai (CREATE)
    private void addEmployee(){

        final String nama = editTextNama.getText().toString().trim();

//        final String jenis = editTextJenis.getText().toString().trim();
        final String deskripsi = editTextAspirasi.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AddAspirasi.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(AddAspirasi.this,s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_EMP_NAMA,nama);

//                params.put(Konfigurasi.KEY_EMP_JENIS,jenis);
                params.put(Konfigurasi.KEY_EMP_DESKRIPSI,deskripsi);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Konfigurasi.URL_ADDAspirasi, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            addEmployee();
            startActivity(new Intent(AddAspirasi.this,Aspirasi.class));
        }

//        if(v == buttonView){
//            startActivity(new Intent(this,TampilSemuaData.class));
//        }
    }
}

