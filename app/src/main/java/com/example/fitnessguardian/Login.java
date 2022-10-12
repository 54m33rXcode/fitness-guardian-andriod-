package com.example.fitnessguardian;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
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

public class Login extends AppCompatActivity {

    EditText Username,Password;
    Button btn_login;
    TextView linkRegister;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Username = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        btn_login = findViewById(R.id.login);
        linkRegister = findViewById(R.id.goto_reg);

        sessionManager = new SessionManager(this);

        linkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserLoginProcess();
            }
        });
    }
    private void UserLoginProcess() {
        String username = Username.getText().toString().trim();
        String password = Password.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()){
            Username.setError("username empty");
            Password.setError("password is empty");
        }else{

            StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.32.42/Fitness_guardian_crud/login1.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String result = jsonObject.getString("status");
                        if (result.equals("success")){
                            sessionManager.UserSessionManager(username,password);

                            Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this,Exercise.class));
                            finish();
                        }else {

                            Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(Login.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            })
            {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("username",username);
                    params.put("password",password);
                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(request);
        }
    }
}