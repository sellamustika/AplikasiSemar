package com.example.aplikasisemar;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;

public class SignIn extends AppCompatActivity {
    private Button btnSignin;
    private EditText etemail,etpassword;
    private FirebaseAuth mAuth;
    private TextView link_regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();

        etemail = findViewById(R.id.etemail);
        etpassword = findViewById(R.id.etpassword);
        link_regist = findViewById(R.id.link_regist);

        link_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrasi();
            }
        });

        btnSignin = findViewById(R.id.btnSignin);


        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }
    private void registrasi(){
        Intent intent = new Intent(this, RegistrasiActivity.class);
        startActivity(intent);
    }

    private void signIn() {
        String email, password;
        email = etemail.getText().toString().trim();
        password = etpassword.getText().toString().trim();

        if (email.equals("")) {
            Toast.makeText(SignIn.this, "Email is required", Toast.LENGTH_SHORT).show();
        } else if (password.equals("")) {
            Toast.makeText(SignIn.this, "Password is required", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6) {
            Toast.makeText(SignIn.this, "Password too short", Toast.LENGTH_SHORT).show();
        } else {

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //go to new activity
                        Intent i = new Intent(SignIn.this,MainActivity.class);
                        startActivity(i);
                        finish();


                    } else {
                        Toast.makeText(SignIn.this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }



}
