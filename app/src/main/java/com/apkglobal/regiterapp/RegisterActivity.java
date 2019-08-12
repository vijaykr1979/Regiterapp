package com.apkglobal.regiterapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
EditText name,email,mobile,password;
Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        mobile=findViewById(R.id.mobile);
        password=findViewById(R.id.password);
        register=findViewById(R.id.register);
        if(isNetworkConnected()){
            Toast.makeText(RegisterActivity.this,"Internet is ON",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(RegisterActivity.this,"Internet is OFF",Toast.LENGTH_LONG).show();
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals("")
                ||email.getText().toString().equals("")
                        ||mobile.getText().toString().equals("")
                        ||password.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this,"Fill all the fields",Toast.LENGTH_LONG).show();
                }
                else
                {
                    String url="http://searchkero.com/vijaysir/register.php";
                    StringRequest sr=new StringRequest(1, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_LONG).show();
                                name.setText("");
                                email.setText("");
                                mobile.setText("");
                                password.setText("");

                                    Intent intent= new Intent(RegisterActivity.this,MainActivity.class);
                                    startActivity(intent);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                           Map<String, String> map = new HashMap<>();
                           map.put("namekey", name.getText().toString());
                            map.put("emailkey", email.getText().toString());
                            map.put("mobilekey", mobile.getText().toString());
                            map.put("passwordkey", password.getText().toString());
                            return map;
                        }
                    };
                    RequestQueue rq= Volley.newRequestQueue(RegisterActivity.this);
                    rq.add(sr);

                }
            }
        });

    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

}
