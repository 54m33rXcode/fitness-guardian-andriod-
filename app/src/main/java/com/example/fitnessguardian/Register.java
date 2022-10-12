package com.example.fitnessguardian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Register extends AppCompatActivity {
    private EditText name,gender,username,password,cpassword;
    private Button btn_reg;
    private ProgressBar loading;
    private static String URL_REGIST = "http://192.168.32.42/Fitness_guardian_crud/register.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        name = findViewById(R.id.name);
        gender = findViewById(R.id.gender);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        cpassword = findViewById(R.id.password_con);
        btn_reg = findViewById(R.id.register);
        loading = findViewById(R.id.loading);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.length()==0){
                    name.setError("enter your name");
                }else if(gender.length()==0){
                    gender.setError("Enter your gender");
                }else if(username.length()==0){
                    username.setError("Set username also");
                }else if( password.length()<6){
                    password.setError("password length should be greater then 6");
                }else if(cpassword.length()==0){
                    cpassword.setError("don't leave this empty too");
                }
                else if (!password.getText().toString().equals(cpassword.getText().toString())) {
                    Toast.makeText(Register.this, "The Password you insert not the same", Toast.LENGTH_SHORT).show();
//                    cpassword.setError("password not matched");
                }
                else{
                    Regist();
                }

            }
        });
    }
    private  void Regist(){
        loading.setVisibility(View.VISIBLE);
        btn_reg.setVisibility(View.GONE);

        final String name = this.name.getText().toString().trim();
        final String gender = this.gender.getText().toString().trim();
        final String username = this.username.getText().toString().trim();
        final String password = this.password.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            String success = object.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(Register.this, "Registered Success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Register.this,Login.class);
                                startActivity(intent);
                                finish();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Register.this, "Registration Error!! else username already exist use unique user name", Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            btn_reg.setVisibility(View.VISIBLE);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Register.this, "Registration Error!!"+error.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        btn_reg.setVisibility(View.VISIBLE);

                    }
                })
        {
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("name",name);
                params.put("gender",gender);
                params.put("username",username);
                params.put("password",password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}