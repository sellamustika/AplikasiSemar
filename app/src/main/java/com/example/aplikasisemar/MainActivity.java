package com.example.aplikasisemar;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName(); //getting the info
    Button btnmenu, ex, st,btn_logout,info,ms;
    RelativeLayout mainconten;
    LinearLayout mainmenu;
    Animation fromtop, frombottom;
    ImageView userpicb, btn_1, btn_2, btn_3, btn_4;
    TextView name, email;

    SessionManager sessionManager;
    String getId;

    private static String URL_READ = "http://192.168.43.126/android_register_login/read_detail.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        btnmenu = (Button) findViewById(R.id.btnmenu);
        ex = (Button) findViewById(R.id.ex);
        st = (Button) findViewById(R.id.st);
        info = (Button) findViewById(R.id.info);
        ms = (Button) findViewById(R.id.ms);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        name = (TextView) findViewById(R.id.name);
       email= (TextView) findViewById(R.id.email);
        btn_1 = (ImageView) findViewById(R.id.btn_1);
        btn_2 = (ImageView) findViewById(R.id.btn_2);
        btn_3 = (ImageView) findViewById(R.id.btn_3) ;
        btn_4 = (ImageView) findViewById(R.id.btn_4);
        userpicb = (ImageView) findViewById(R.id.userpicb);

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);


        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);

        mainconten = (RelativeLayout) findViewById(R.id.maincontent);

        mainmenu = (LinearLayout) findViewById(R.id.mainmenu);

        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainconten.animate().translationX(0);
                mainmenu.animate().translationX(0);

                ex.startAnimation(frombottom);
                st.startAnimation(frombottom);
                btn_logout.startAnimation(frombottom);

                name.startAnimation(fromtop);
                email.startAnimation(fromtop);
                userpicb.startAnimation(fromtop);


            }
        });
        mainconten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainconten.animate().translationX(-570);
                mainmenu.animate().translationX(-635);

            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPengaduanActivity();
            }
        });
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKisJkn();
            }
        });
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIuran();
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInfoPendaftaran();
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSideKis();
            }
        });

        ms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openalurDaftar();
            }
        });

        st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpengaduan();
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });


    }

    private void openpengaduan() {
        Intent intent = new Intent(this, BoardActivity.class);
        startActivity(intent);
    }

    private void openalurDaftar() {
        Intent intent = new Intent(this, Info_pendaftaran.class);
        startActivity(intent);
    }

    private void openSideKis() {
        Intent intent = new Intent(this, KisJkn.class);
        startActivity(intent);
    }

    private void openIuran() {
        Intent intent = new Intent(this, Iuran.class);
        startActivity(intent);
    }

    private void openKisJkn() {
        Intent intent = new Intent(this, KisJkn.class);
        startActivity(intent);
    }

    private void openInfoPendaftaran() {
        Intent intent = new Intent(this, Info_pendaftaran.class);
        startActivity(intent);
    }


    public void openPengaduanActivity() {
        Intent intent = new Intent(this, BoardActivity.class);
        startActivity(intent);
    }


    //getUserDetail
    private void getUserDetail(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG, response.toString());

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if (success.equals("1")){

                                for (int i =0; i < jsonArray.length(); i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String strName = object.getString("name").trim();
                                    String strEmail = object.getString("email").trim();

                                    name.setText(strName);
                                    email.setText(strEmail);

                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Error Reading Detail "+e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Error Reading Detail "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String > params = new HashMap<>();
                params.put("id", getId);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserDetail();
    }

//    public void openSignIn(){
//        Intent intent = new Intent(this, SignIn.class);
//        startActivity(intent);
//
//    }

    }

