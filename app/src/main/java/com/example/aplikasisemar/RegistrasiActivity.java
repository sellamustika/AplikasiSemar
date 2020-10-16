package com.example.aplikasisemar;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;

public class RegistrasiActivity extends AppCompatActivity {
    private Button btn_regist;
    private EditText etemail, etpassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
        mAuth = FirebaseAuth.getInstance();

        etemail = findViewById(R.id.etemail);
        etpassword = findViewById(R.id.etpassword);

        btn_regist = findViewById(R.id.btn_regist);
        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();

            }
        });
    }
    private void signUp(){
        String email,password;
        email = etemail.getText().toString().trim();
        password = etpassword.getText().toString().trim();

        if (email.equals("")){
            Toast.makeText(RegistrasiActivity.this, "Email Required",Toast.LENGTH_SHORT).
                    show();
        }
        else if(password.equals("")){
            Toast.makeText(RegistrasiActivity.this, "Password Required",Toast.LENGTH_SHORT).
                    show();

        }
        else if(password.length() < 6) {
            Toast.makeText(RegistrasiActivity.this, "Password too short", Toast.LENGTH_SHORT).
                    show();
        }
        else {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegistrasiActivity.this, "Registered successfully", Toast.LENGTH_SHORT).
                                show();
                        Intent i = new Intent(RegistrasiActivity.this,SignIn.class);
                        startActivity(i);
                        finish();
                    }else {
                        Toast.makeText(RegistrasiActivity.this, "Registration failure", Toast.LENGTH_SHORT).
                                show();
                    }
                }
            });
        }

    }
}
