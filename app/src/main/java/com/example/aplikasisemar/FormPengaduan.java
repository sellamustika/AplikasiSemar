package com.example.aplikasisemar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FormPengaduan extends AppCompatActivity {
    TextView titlepage, subtitlepage;
    Button btnAddNew;
    DatabaseReference reference;
    RecyclerView ourdoes;
    ArrayList<MyData>list;
    DataAdapter dataAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pengaduan);

        titlepage = findViewById(R.id.titlepage);
        subtitlepage = findViewById(R.id.subtitlepage);
        btnAddNew = findViewById(R.id.btnAddNew);

        Typeface mlight = Typeface.createFromAsset(getAssets(), "font/monlight.ttf");
        Typeface mmedium = Typeface.createFromAsset(getAssets(), "font/monmedium.ttf");

        titlepage.setTypeface(mmedium);
        subtitlepage.setTypeface(mlight);

        // working with data
        ourdoes = findViewById(R.id.ourdoes);
        ourdoes.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<MyData>();

        //get data from firebase
        reference = FirebaseDatabase.getInstance().getReference().child("bpjssemar");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //code to retrive data and replace layout
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    MyData p = dataSnapshot1.getValue(MyData.class);
                    list.add(p);
                }
                dataAdapter = new DataAdapter(FormPengaduan.this, list);
                ourdoes.setAdapter(dataAdapter);
                dataAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //code to show error
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
